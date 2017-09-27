package io.legaldocml.io.impl;

import io.legaldocml.io.AttributeConsumer;
import io.legaldocml.util.CharArray;
import io.legaldocml.util.CharArrays;
import io.legaldocml.util.CharBuffer;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.Namespaces;
import io.legaldocml.io.ProcessingInstruction;
import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlReaderContext;
import io.legaldocml.unsafe.UnsafeHelper;
import io.legaldocml.util.ToStringBuilder;

import javax.xml.stream.XMLStreamConstants;
import java.nio.MappedByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static io.legaldocml.io.impl.XmlChannelReaderException.Type.EXPECTED_EQUALS;
import static io.legaldocml.io.impl.XmlChannelReaderException.Type.EXPECTED_GREATER;
import static io.legaldocml.io.impl.XmlChannelReaderException.Type.EXPECTED_QUOTES;
import static io.legaldocml.io.impl.XmlChannelReaderException.Type.ILLEGAL_CHAR_0000;
import static io.legaldocml.io.impl.XmlChannelReaderException.Type.INVALID_EVENT_TYPE_PI;
import static io.legaldocml.io.impl.XmlChannelReaderException.Type.INVALID_EVENT_TYPE_QNAME;
import static io.legaldocml.io.impl.XmlChannelReaderException.Type.INVALID_EVENT_TYPE_TEXT;
import static io.legaldocml.io.impl.XmlChannelReaderException.Type.PREMATURE_END_OF_FILE;
import static io.legaldocml.io.impl.XmlChannelReaderException.Type.REPLACE_ENTITY;
import static io.legaldocml.io.impl.XmlChannelReaderException.Type.STATE_UNKOWN;
import static io.legaldocml.io.impl.XmlChannelReaderException.Type.UNEXPECTED_TAG;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class XmlChannelReader implements XMLStreamConstants, XmlChannelReaderStates, XmlReader {

    /**
     * number of sequence
     */
    private static final int SEQUENCE_LENGTH = 128;
    /**
     * Holder for Unsafe.
     */
    @SuppressWarnings("all")
    private static final sun.misc.Unsafe UNSAFE = UnsafeHelper.getUnsafe();

    /**
     * Holds working stack (by nesting level).
     */
    private final QNameImpl[] elemStack = new QNameImpl[SEQUENCE_LENGTH];

    @SuppressWarnings("unchecked")
    private final Consumer<NamespacesImpl>[] nsStack = new Consumer[SEQUENCE_LENGTH];


    /**
     * Cache for Qnames.
     */
    private final Map<Integer, QNameImpl> elemStackCache = new HashMap<>(256, 0.9f);

    /**
     * Holds the current attributes.
     */
    private final XmlAttributes attributes;

    /**
     * Holds the namespace stack.
     */
    private final NamespacesImpl namespaces = new NamespacesImpl();

    /**
     * Holds qualified name (include prefix).
     */
    private QNameImpl qName;

    /**
     * Holds element prefix separator index.
     */
    private int prefixSep;

    /**
     * Holds attribute qualified name.
     */
    private CharBuffer attrQName;

    /**
     * Holds attribute prefix separator index.
     */
    private int attrPrefixSep;
    /**
     * Indicates if event type is START_TAG, and tag is empty, i.e. <sometag/>.
     */
    private boolean isEmpty;

    /**
     * Mapped byte buffer ref.
     */
    private MappedByteBuffer buf;

    /**
     * memory adr;
     */
    private long adr;

    /**
     * Holds the parser state.
     */
    private int state = STATE_CHARACTERS;

    /**
     * Holds current event type.
     */
    private int eventType = START_DOCUMENT;

    /**
     * Holds the current element nesting.
     */
    private int depth;

    private final CharBuffer[] seqs = new CharBuffer[SEQUENCE_LENGTH];

    private int seqsIdx = 0;

    private CharBuffer cb;

    private long max;

    /**
     * Holds the current text.
     */
    private CharArray text;

    /**
     * Indicates if characters are pending for potential coalescing.
     */
    private boolean charactersPending = false;

    /**
     * Holds the prolog if any.
     */
    private ProcessingInstruction prolog;

    /**
     * Set the XML Reader Context for this reader.
     */
    private XmlReaderContext context;

    /**
     * Depth element that hold preserve space.
     */
    private int preserveSpace = -1;

    private final boolean clearcache;

    public XmlChannelReader() {
        this(true);
    }

    public XmlChannelReader(boolean clearCache) {
        this.clearcache = clearCache;
        this.attributes = new XmlAttributes();
        for (int i = 0; i < SEQUENCE_LENGTH; i++) {
            this.seqs[i] = new CharBuffer();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QName getQName() {
        if ((eventType != START_ELEMENT) && (eventType != END_ELEMENT)) {
            throw new XmlChannelReaderException(INVALID_EVENT_TYPE_QNAME, this);
        }
        return qName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final CharArray getText() {
        if ((eventType != CHARACTERS) && (eventType != COMMENT) && (eventType != DTD)) {
            throw new XmlChannelReaderException(INVALID_EVENT_TYPE_TEXT, this);
        }
        return text;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final ProcessingInstruction getPIData() {
        if (eventType != PROCESSING_INSTRUCTION) {
            throw new XmlChannelReaderException(INVALID_EVENT_TYPE_PI, this);
        }
        return new ProcessingInstructionImpl(cb);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Namespaces getNamespaces() {
        return this.namespaces;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void preserveSpace() {
        this.preserveSpace = this.depth;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public  <T extends XmlReaderContext> void setContext(T context) {
        this.context = context;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends XmlReaderContext> T getContext() {
        return (T) this.context;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int next() {
        // Clears previous state.
        if (eventType == START_ELEMENT) {
            if (isEmpty) {
                // Previous empty tag, generates END_TAG automatically.
                isEmpty = false;
                eventType = END_ELEMENT;
                return END_ELEMENT;
            }
        } else if (eventType == END_ELEMENT) {
            depth--;
            if (this.nsStack[depth] != null) {
                this.nsStack[depth].accept(this.namespaces);
                this.nsStack[depth] = null;
            }
        }

        // Reader loop.
        while (true) {
            if (isEndOfBuffer()) {
                return eventType;
            }

            char c = nextChar();

            here:
            switch (state) {
                case STATE_CHARACTERS: {
                    char previous = 0x20;
                    while (true) {
                        // Read characters data all at once.
                        if (c == '<') {
                            if (cb.length() > 0) {
                                if (charactersPending) {
                                    // Coalescing.
                                    throw new UnsupportedOperationException();
                                } else {
                                    text = cb;
                                    seq();
                                    charactersPending = true;
                                }
                            }
                            state = STATE_MARKUP;
                            break here;
                        }

                        if (c <= '&') {
                            c = (c == '&') ? doReplaceEntity() : (c < ' ') ? handleEndOfLine(c) : c;
                        }

                        if (this.preserveSpace == -1) {
                            if (c >= 0x20 && (c != 0x20 || previous != 0x20)) {
                                cb.put(c);
                            } // else { skip }
                        } else {
                            cb.put(c);
                        }

                        // Local character reading block.
                        previous = c;
                        c = nextChar();
                    }
                }
                case STATE_CDATA:
                    cb.put(c);
                    cb.put(nextChar());
                    cb.put(nextChar());

                    while (true) {
                        if (cb.charAt(cb.pos() - 1) == '>' && cb.charAt(cb.pos() - 2) == ']' && cb.charAt(cb.pos() - 3) == ']') {
                            // -> end of cdata
                            cb.setPos(cb.pos() - 3);
                            state = STATE_CHARACTERS;
                            break;
                        }
                        cb.put(nextChar());
                    }
                    break;

                case STATE_DTD:
                    if (c == '>') {
                        // Do not keep DTD.
                        state = STATE_CHARACTERS;
                        eventType = DTD;
                        return DTD;
                    } else if (c == '[') {
                        state = STATE_DTD_INTERNAL;
                    }
                    break;

                case STATE_DTD_INTERNAL:
                    if (c == ']') {
                        state = STATE_DTD;
                    }
                    break;

                // Starts with '<'
                case STATE_MARKUP:
                    if (cb.length() == 0) {
                        if (c == '/') {
                            state = STATE_CLOSE_TAG_READ_ELEM_NAME;
                            prefixSep = -1;
                            if (charactersPending) {
                                // Flush characters event.
                                charactersPending = false;
                                eventType = CHARACTERS;
                                return CHARACTERS;
                            }
                        } else if (c == '?') {
                            state = STATE_PI;
                            // Flush characters event.
                            if (charactersPending) {
                                charactersPending = false;
                                eventType = CHARACTERS;
                                return CHARACTERS;
                            }
                            // Element tag (first letter).
                        } else if (c != '!') {
                            cb.put(c);
                            state = STATE_OPEN_TAG_READ_ELEM_NAME;
                            prefixSep = -1;
                            // Flush character event.
                            if (charactersPending) {
                                charactersPending = false;
                                eventType = CHARACTERS;
                                return CHARACTERS;
                            }
                        } else {
                            // c == '!'
                            char c1 = nextChar();
                            char c2 = nextChar();
                            if (c1 == '-' && c2 == '-') {
                                this.state = STATE_COMMENT;
                                if (this.charactersPending) {
                                    charactersPending = false;
                                    eventType = CHARACTERS;
                                    return CHARACTERS;
                                }
                            }
                            // maybe CDATA => check
                            if (c1 == '[' && c2 == 'C' && nextChar() == 'D' && nextChar() == 'A' && nextChar() == 'T' && nextChar() == 'A' && nextChar() == '[') {
                                this.state = STATE_CDATA;
                            }
                        }
                    }
                    // no else because ignores, e.g. <!ELEMENT <!ENTITY...
                    break;

                case STATE_COMMENT:

                    while (true) {

                        if (c == '>' && cb.length() > 2 && cb.getBefore(1) == '-' && cb.getBefore(2) == '-') {
                            text = cb;
                            seq();
                            eventType = COMMENT;
                            state = STATE_CHARACTERS;
                            return COMMENT;
                        }

                        cb.put(c);
                        c = nextChar();

                    }

                case STATE_PI: {
                    cb.put(c);
                    if ((c == '>') && (cb.pos() >= 2) && (cb.charAt(cb.pos() - 2) == '?')) {
                        // cb contains <? and ?>
                        state = STATE_CHARACTERS;
                        eventType = PROCESSING_INSTRUCTION;
                        return PROCESSING_INSTRUCTION;
                    }
                    break;
                }

                // OPEN_TAG:
                case STATE_OPEN_TAG_READ_ELEM_NAME:
                    attributes.reset();
                    while (true) {
                        if (c < '@') {
                            if (c == '>') {
                                elemStack[depth++] = qname(cb);
                                seq();
                                state = STATE_CHARACTERS;
                                isEmpty = false;
                                eventType = START_ELEMENT;
                                return START_ELEMENT;
                            } else if (c == '/') {
                                elemStack[depth++] = qname(cb);
                                seq();
                                state = STATE_OPEN_TAG_EMPTY_TAG;
                                break;
                            } else if (c == ':') {
                                prefixSep = cb.pos();
                            } else if (c <= ' ') {
                                elemStack[depth++] = qname(cb);
                                seq();
                                state = STATE_OPEN_TAG_ELEM_NAME_READ;
                                break;
                            }
                        }

                        cb.put(c);
                        c = nextChar();
                    }
                    break;
                case STATE_OPEN_TAG_ELEM_NAME_READ:
                    if (c == '>') {
                        state = STATE_CHARACTERS;
                        isEmpty = false;
                        eventType = START_ELEMENT;
                        return START_ELEMENT;
                    } else if (c == '/') {
                        state = STATE_OPEN_TAG_EMPTY_TAG;
                    } else if (c > ' ') {
                        cb.reset();
                        cb.put(c);
                        attrPrefixSep = -1;
                        state = STATE_OPEN_TAG_READ_ATTR_NAME;
                    }
                    break;

                case STATE_OPEN_TAG_READ_ATTR_NAME:
                    while (true) {
                        if (c < '@') {
                            // Else avoid multiple checks.
                            if (c <= ' ') {
                                attrQName = cb;
                                seq();
                                state = STATE_OPEN_TAG_ATTR_NAME_READ;
                                break;
                            } else if (c == '=') {
                                attrQName = cb;
                                seq();
                                state = STATE_OPEN_TAG_EQUAL_READ;
                                break;
                            } else if (c == ':') {
                                attrPrefixSep = cb.pos();
                            }
                        }
                        cb.put(c);
                        c = nextChar();
                    }
                    break;

                case STATE_OPEN_TAG_ATTR_NAME_READ:
                    if (c == '=') {
                        state = STATE_OPEN_TAG_EQUAL_READ;
                    } else if (c > ' ') {
                        throw new XmlChannelReaderException(EXPECTED_EQUALS, this);
                    }
                    break;

                case STATE_OPEN_TAG_EQUAL_READ:
                    if (c == '\'') {
                        state = STATE_OPEN_TAG_READ_ATTR_VALUE_SIMPLE_QUOTE;
                    } else if (c == '\"') {
                        state = STATE_OPEN_TAG_READ_ATTR_VALUE_DOUBLE_QUOTE;
                    } else if (c > ' ') {
                        throw new XmlChannelReaderException(EXPECTED_QUOTES, this);
                    }
                    break;

                case STATE_OPEN_TAG_READ_ATTR_VALUE_SIMPLE_QUOTE:
                    while (true) {
                        if (c == '\'') {
                            processAttribute(cb);
                            seq();
                            state = STATE_OPEN_TAG_ELEM_NAME_READ;
                            break;
                        }
                        cb.put(c);
                        c = nextChar();
                        if (c == '&') {
                            c = doReplaceEntity();
                        }
                    }
                    break;

                case STATE_OPEN_TAG_READ_ATTR_VALUE_DOUBLE_QUOTE:
                    while (true) {
                        if (c == '\"') {
                            processAttribute(cb);
                            seq();
                            state = STATE_OPEN_TAG_ELEM_NAME_READ;
                            break;
                        }
                        cb.put(c);
                        c = nextChar();
                        if (c == '&') {
                            c = doReplaceEntity();
                        }
                    }
                    break;

                case STATE_OPEN_TAG_EMPTY_TAG:
                    if (c == '>') {
                        state = STATE_CHARACTERS;
                        isEmpty = true;
                        eventType = START_ELEMENT;
                        return START_ELEMENT;
                    } else {
                        throw new XmlChannelReaderException(EXPECTED_GREATER, this);
                    }

                    // CLOSE_TAG:
                case STATE_CLOSE_TAG_READ_ELEM_NAME:
                    while (true) {
                        if (c < '@') {
                            if (c == '>') {
                                qname(cb);
                                state = STATE_CHARACTERS;
                                if (!qName.doEquals(elemStack[depth - 1])) {
                                    throw new XmlChannelReaderException(UNEXPECTED_TAG, this);
                                }
                                eventType = END_ELEMENT;

                                if (this.preserveSpace == this.depth) {
                                    this.preserveSpace = -1;
                                }

                                return END_ELEMENT;
                            } else if (c == ':') {
                                prefixSep = cb.pos();
                            } else if (c <= ' ') {
                                qname(cb);
                                state = STATE_CLOSE_TAG_ELEM_NAME_READ;
                                break;
                            }
                        }
                        cb.put(c);
                        c = nextChar();
                    }
                    break;

                case STATE_CLOSE_TAG_ELEM_NAME_READ:
                    if (c == '>') {
                        state = STATE_CHARACTERS;
                        if (!qName.doEquals(elemStack[depth])) {
                            throw new XmlChannelReaderException(UNEXPECTED_TAG, this);
                        }
                        eventType = END_ELEMENT;

                        if (this.preserveSpace == this.depth) {
                            this.preserveSpace = -1;
                        }

                        return END_ELEMENT;
                    } else if (c > ' ') {
                        throw new XmlChannelReaderException(EXPECTED_GREATER, this);
                    }
                    break;

                default:
                    throw new XmlChannelReaderException(STATE_UNKOWN, this);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void nextStartOrEndElement() {
        if (this.eventType == END_DOCUMENT) {
            return;
        }
        do {
            eventType = next();
            if (eventType == XMLStreamConstants.START_ELEMENT) {
                return;
            }
            if (eventType == XMLStreamConstants.END_ELEMENT) {
                return;
            }
        } while (true);
    }

    public final void setBuffer(MappedByteBuffer buf) {
        this.buf = buf;

        this.adr = Buffers.address(buf);
        this.max = adr + buf.limit();

        cb = seqs[0];
        seqsIdx = 0;
        buf.mark();

        // Reads prolog (if there)
        if (buf.remaining() > 4 && (buf.get() == '<') && (buf.get() == '?') && (buf.get() == 'x') && (buf.get() == 'm') && (buf.get() == 'l')
                && (buf.get() == ' ')) {
            buf.reset();
            // Prolog detected.
            next();
            // Processing instruction.
            prolog = getPIData();
            // Resets to START_DOCUMENT.
            eventType = START_DOCUMENT;
        } else {
            buf.reset();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getEventType() {
        return eventType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getDepth() {
        return this.depth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final ProcessingInstruction getProlog() {
        return this.prolog;
    }

    protected void reset() {
        this.buf = null;
        this.adr = 0;
        this.prolog = null;
        this.context = null;
        this.charactersPending = false;
        this.eventType = -1;
        this.state = STATE_CHARACTERS;
        this.isEmpty = false;
        this.attrPrefixSep = 0;
        this.depth = 0;
        if (this.clearcache) {
            elemStackCache.clear();
        }
        this.attrQName = null;
        this.cb = null;
        this.seqsIdx = 0;
        this.text = null;
        for (int i = 0; i < SEQUENCE_LENGTH; i++) {
            if (this.elemStack[i] == null) {
                break;
            }
            this.elemStack[i] = null;
            this.seqs[i].reset();
        }
        this.namespaces.reset();
    }

    /**
     * {@inheritDoc}
     */
    public void forEach(Externalizable object, AttributeConsumer consumer) {
        attributes.forEach(object, consumer);
    }

    /**
     * @return
     */
    @SuppressWarnings("all")
    private char nextChar() {

        if (this.adr == this.max) {
            throw new XmlChannelReaderException(PREMATURE_END_OF_FILE, this);
        }

        byte c = UNSAFE.getByte(adr++);

        if ((c & 0x80) == 0) {
            return (char) c;
        }

        if ((c & 0xE0) == 0xC0) {
            // 2 bytes
            return (char) (((c & 0x1F) << 6) | (UNSAFE.getByte(adr++) & 0x3F));
        } else if ((c & 0xF0) == 0xE0) {
            // 3 bytes
            return (char) (((((c & 0x0F) << 6) | (UNSAFE.getByte(adr++) & 0x3F)) << 6) | (UNSAFE.getByte(adr++) & 0x3F));
        } else if ((c & 0xF8) == 0xF0) {
            // 4 bytes
            return (char) (((((((c & 0x7F) << 6) | (UNSAFE.getByte(adr++) & 0x3F)) << 6) | (UNSAFE.getByte(adr++) & 0x3F)) << 6) |
                    (UNSAFE.getByte(adr++) & 0x3F));
        } else if ((c & 0xFC) == 0xF8) {
            // 5 bytes
            return (char) (((((((((c & 0x03) << 6) | (UNSAFE.getByte(adr++) & 0x3F)) << 6) | (UNSAFE.getByte(adr++) & 0x3F)) << 6) |
                    (UNSAFE.getByte(adr++) & 0x3F)) << 6) | (UNSAFE.getByte(adr++) & 0x3F));
        } else if ((c & 0xFE) == 0xFC) {
            // 6 bytes
            return (char) (((((((((((c & 0x01) << 6) | (UNSAFE.getByte(adr++) & 0x3F)) << 6) | (UNSAFE.getByte(adr++) & 0x3F)) << 6) |
                    (UNSAFE.getByte(adr++) & 0x3F)) << 6) | (UNSAFE.getByte(adr++) & 0x3F)) << 6) | (UNSAFE.getByte(adr++) & 0x3F));
        } else {
            throw new UnsupportedOperationException("byte [" + c + "] -> [" + (c & 0xF0) + "] to char ??? -> [" + this.cb + "]");
        }

    }

    private void processAttribute(CharBuffer attrValue) {
        // No prefix.
        if (attrPrefixSep < 0) {
            if (isXMLNS(attrQName)) {
                // Sets default namespace.
                namespaces.setPrefixDefault(attrValue);
            } else {
                attributes.addAttribute(attrQName, 0, attrValue);
            }
            return;
        }
        CharBuffer cb = attrQName;
        // Prefix.
        if (cb.length() > 6 && (cb.charAt(0) == 'x') && (cb.charAt(1) == 'm') && (cb.charAt(2) == 'l') && (cb.charAt(3) == 'n') && (cb.charAt(4) == 's')) {
            this.namespaces.setPrefix(this, CharArrays.immutable(cb, 6, cb.length() - 6), CharArrays.immutable(attrValue));
            if (this.nsStack[depth - 1] == null) {
                this.nsStack[depth - 1] = NamespacesImpl.POP_ACTION;
            } else {
                this.nsStack[depth - 1] = this.nsStack[depth - 1].andThen(NamespacesImpl.POP_ACTION);
            }


        } else {
            attributes.addAttribute(attrQName, attrPrefixSep, attrValue);
        }

    }

    private static boolean isXMLNS(CharArray chars) {
        return (chars.length() == 5) &&
                (chars.charAt(0) == 'x') &&
                (chars.charAt(1) == 'm') &&
                (chars.charAt(2) == 'l') &&
                (chars.charAt(3) == 'n') &&
                (chars.charAt(4) == 's');
    }

    private QNameImpl qname(CharBuffer buf) {
        int hashCode = cb.hashCode();
        QNameImpl cacheQName = elemStackCache.get(hashCode);
        if (cacheQName == null) {
            cacheQName = new QNameImpl(cb.value(), cb.pos(), prefixSep);
            this.elemStackCache.put(hashCode, cacheQName);
        }
        this.qName = cacheQName;
        buf.reset();
        return cacheQName;
    }

    private void seq() {
        seqsIdx = (seqsIdx + 1) % SEQUENCE_LENGTH;
        cb = seqs[seqsIdx];
        cb.reset();
    }

    /**
     * Replaces an entity if the current state allows it.
     *
     * @return the next character after the text replacement or '&' if no replacement took place.
     */
    @SuppressWarnings("restriction")
    private char doReplaceEntity() {
        if ((state == STATE_COMMENT) || (state == STATE_PI) || (state == STATE_CDATA)) {
            // (&2.4)
            return '&';
        }

        if (this.adr == this.max) {
            throw new XmlChannelReaderException(PREMATURE_END_OF_FILE, this);
        }

        byte b = UNSAFE.getByte(adr++);

        if (b == 'a') {
            // &amp;
            b = UNSAFE.getByte(adr++);
            if (b == 'm') {
                // check &amp;
                if ('p' == UNSAFE.getByte(adr++) && ';' == UNSAFE.getByte(adr++)) {
                    return '&';
                } else {
                    throw new XmlChannelReaderException(REPLACE_ENTITY, this);
                }
            } else if (b == 'p') {
                // &apos;
                if ('o' == UNSAFE.getByte(adr++) && 's' == UNSAFE.getByte(adr++) && ';' == UNSAFE.getByte(adr++)) {
                    return '\'';
                } else {
                    throw new XmlChannelReaderException(REPLACE_ENTITY, this);
                }
            } else {
                throw new XmlChannelReaderException(REPLACE_ENTITY, this);
            }
        } else if (b == 'g') {
            // &gt;
            if ('t' == UNSAFE.getByte(adr++) && ';' == UNSAFE.getByte(adr++)) {
                return '>';
            } else {
                throw new XmlChannelReaderException(REPLACE_ENTITY, this);
            }
        } else if (b == 'l') {
            // &lt;
            if ('t' == UNSAFE.getByte(adr++) && ';' == UNSAFE.getByte(adr++)) {
                return '<';
            } else {
                throw new XmlChannelReaderException(REPLACE_ENTITY, this);
            }
        } else if (b == '#') {

            char c = nextChar();
            if (c == 'x') {
                // base 16
                throw new UnsupportedOperationException();
            } else {
                // base 10
                int charValue = 0;
                while (c != ';') {
                    charValue *= 10;
                    charValue += (c - 48);
                    c = nextChar();
                }
                return (char) charValue;
            }
        } else if (b == 'q') {
            // &quot;
            if ('u' == UNSAFE.getByte(adr++) && 'o' == UNSAFE.getByte(adr++) && 't' == UNSAFE.getByte(adr++) && ';' == UNSAFE.getByte(adr++)) {
                return '"';
            } else {
                throw new XmlChannelReaderException(REPLACE_ENTITY, this);
            }
        } else {
            throw new XmlChannelReaderException(REPLACE_ENTITY, this);
        }
    }

    /**
     * Detects end of buffer.
     */
    private boolean isEndOfBuffer() {
        if (adr >= max) {
            if (eventType == END_DOCUMENT) {
                throw new XmlChannelReaderException(PREMATURE_END_OF_FILE, this);
            }

            if (cb.length() > 0) {
                if (charactersPending) {
                    text = cb;
                    seq();
                } else {
                    seq();
                    text = cb;
                }
                eventType = CHARACTERS;
            } else {
                eventType = END_DOCUMENT;
            }
            return true;
        }
        return false;
    }

    /**
     * Handles end of line as per XML Spec. 2.11
     *
     * @param c the potential end of line character.
     * @return the replacement character for end of line.
     */
    private char handleEndOfLine(char c) {
        if (c == 0xD) {
            // Replaces #xD with #xA
            // Unless next char is #xA, then skip, #xD#xA will be replaced by #xA
            c = (char) 0xA;
        }
        if (c == 0x0) {
            throw new XmlChannelReaderException(ILLEGAL_CHAR_0000, this);
        }
        return c;
    }


    int getState() {
        return state;
    }

    int getSeqsIdx() {
        return seqsIdx;
    }

    CharBuffer getCb() {
        return cb;
    }

    int getPosition() {
        return (this.buf.limit() - (int) (this.max - this.adr));
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(false);
        XmlChannelReaderException.dump(builder, this);
        return builder.toString();
    }

}
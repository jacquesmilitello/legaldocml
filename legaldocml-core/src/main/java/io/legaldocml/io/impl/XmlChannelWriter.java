package io.legaldocml.io.impl;

import io.legaldocml.LegalDocMlException;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.unsafe.UnsafeHelper;
import io.legaldocml.unsafe.UnsafeString;
import sun.misc.Unsafe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class XmlChannelWriter implements XmlWriter {

    /**
     * Constant Char in byte for the start tag.
     */
    private static final byte START_TAG = '<';
    /**
     * Constant Char in byte for the end tag.
     */
    private static final byte END_TAG = '>';
    /**
     * Constant Char in byte for one space.
     */
    private static final byte SPACE = ' ';
    /**
     * Constant Char in byte for the equal.
     */
    private static final byte EQUALS = '=';
    /**
     * Constant Char in byte for double quote.
     */
    private static final byte DOUBLE_QUOTE = '"';

    private static final byte CHAR_T = 'T';

    private static final byte CHAR_DASH = '-';

    private static final byte CHAR_COLON = ':';

    /**
     * Unsafe instance for memory maniplulation.
     */
    private static final Unsafe UNSAFE = UnsafeHelper.getUnsafe();

    /**
     * Xml Entity &amp; in byte array format.
     */
    private static final MappedByteBuffer BUFFER_ENTITY_AMP = Buffers.direct(new byte[]{'&', 'a', 'm', 'p', ';'});
    private static final long ADDRESS_ENTITY_AMP = Buffers.address(BUFFER_ENTITY_AMP);
    /**
     * Xml Entity &lt; in byte array format.
     */
    private static final MappedByteBuffer BUFFER_ENTITY_LT = Buffers.direct(new byte[]{'&', 'l', 't', ';'});
    private static final long ADDRESS_ENTITY_LT = Buffers.address(BUFFER_ENTITY_LT);
    /**
     * Xml Entity &gt; in byte array format.
     */
    private static final MappedByteBuffer BUFFER_ENTITY_GT = Buffers.direct(new byte[]{'&', 'g', 't', ';'});
    private static final long ADDRESS_ENTITY_GT = Buffers.address(BUFFER_ENTITY_GT);
    /**
     * Xml Entity &quot; in byte array format.
     */
    private static final MappedByteBuffer BUFFER_ENTITY_QUOT = Buffers.direct(new byte[]{'&', 'q', 'u', 'o', 't', ';'});
    private static final long ADDRESS_ENTITY_QUOT = Buffers.address(BUFFER_ENTITY_QUOT);
    /**
     * End tag ("/>") in byte array format.
     */
    private static final MappedByteBuffer BUFFER_END_SINGLE_TAG = Buffers.direct(new byte[]{'/', '>'});
    private static final long ADDRESS_END_SINGLE_TAG = Buffers.address(BUFFER_END_SINGLE_TAG);

    /**
     * Array End ("</") in byte array format.
     */
    private static final MappedByteBuffer BUFFER_ARRAY_END = Buffers.direct(new byte[]{'<', '/'});
    private static final long ADDRESS_ARRAY_END = Buffers.address(BUFFER_ARRAY_END);

    /**
     * Header for xml file (prolog) in byte array format.
     */
    private static final MappedByteBuffer BUF_HEADER = Buffers
            .direct(new byte[]{'<', '?', 'x', 'm', 'l', ' ', 'v', 'e', 'r', 's', 'i', 'o', 'n', '=', '"', '1', '.', '0', '"', ' ', 'e',
                    'n', 'c', 'o', 'd', 'i', 'n', 'g', '=', '"', 'U', 'T', 'F', '-', '8', '"', '?', '>'});
    private static final long BUF_HEADER_ADDRESS = Buffers.address(BUF_HEADER);

    /**
     * Start the PI instruction.
     */
    private static final byte[] PI_START = new byte[]{'<', '?'};

    /**
     * End the PI instruction.
     */
    private static final byte[] PI_END = new byte[]{'?', '>'};

    /**
     * Stack of elements
     */
    private final boolean[] hasElements = new boolean[128];

    /**
     * Pointer for elements.
     */
    private int elem = 0;

    /**
     * Stack of elements
     */
    private final long[] namespaces = new long[8];

    /**
     * Stack of elements
     */
    private final long[] namespacesSize = new long[8];

    /**
     * Pointer for elements.
     */
    private int namespacesPtr = -1;

    /**
     * Channel to write elements, ...
     */
    private WritableByteChannel channel;

    /**
     * Internal byteBuffer.
     */
    private final MappedByteBuffer buffer = (MappedByteBuffer) ByteBuffer.allocateDirect(32768);

    /**
     * Internal Address.
     */
    private final Long address;

    private boolean permissive;

    private List<LegalDocMlException> exceptions;

    /**
     * Private constructor : for poolableObject.
     */
    public XmlChannelWriter() {
        this.address = Buffers.address(this.buffer);
        this.permissive = false;
    }

    /**
     * @param channel the channel to set
     */
    public void setChannel(WritableByteChannel channel) {
        this.channel = channel;
    }

    /**
     * Flush the current outputStream.
     */
    public void flush() throws IOException {
        this.buffer.flip();
        this.channel.write(this.buffer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeStartDocument(long address, int len) {
        this.buffer.clear();
        UNSAFE.copyMemory(BUF_HEADER_ADDRESS, this.address, 38);
        int pos = 38;
        UNSAFE.putByte(this.address + (pos++), START_TAG);
        UNSAFE.copyMemory(address, this.address + (pos), len);
        pos += len;
        this.buffer.position(pos);
        hasElements[++elem] = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeEndDocument(long address, int len) throws IOException {
        int pos = this.buffer.position();
        UNSAFE.copyMemory(ADDRESS_ARRAY_END, this.address + pos, 2);
        pos += 2;
        UNSAFE.copyMemory(address, this.address + pos, len);
        UNSAFE.putByte(this.address + pos++ + len, END_TAG);
        this.buffer.position(pos + len);
    }

    /**
     * {@inheritDoc}
     */
    public void writeAttribute(long name, int nameLen, char[] value) throws IOException {
        checkSize(nameLen + value.length + 4);

        int pos = this.buffer.position();
        UNSAFE.putByte(this.address + pos++, SPACE);
        UNSAFE.copyMemory(name, this.address + pos, nameLen);
        pos += nameLen;
        UNSAFE.putByte(this.address + pos++, EQUALS);
        UNSAFE.putByte(this.address + pos++, DOUBLE_QUOTE);
        pos = raw(value, 0, value.length, pos);
        UNSAFE.putByte(this.address + pos++, DOUBLE_QUOTE);
        this.buffer.position(pos);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeAttribute(long name, int nameLen, byte[] value) throws IOException {
        checkSize(nameLen + value.length + 16);

        long address = this.address + this.buffer.position();
        UNSAFE.putByte(address++, SPACE);
        UNSAFE.copyMemory(name, address, nameLen);
        address += nameLen;
        UNSAFE.putByte(address++, EQUALS);
        UNSAFE.putByte(address++, DOUBLE_QUOTE);
        UNSAFE.copyMemory(value, UnsafeHelper.BYTE_ARRAY_BASE_OFFSET, null, address, value.length);
        address += value.length;
        UNSAFE.putByte(address++, DOUBLE_QUOTE);
        this.buffer.position((int) (address - this.address));
    }

    /**
     * {@inheritDoc}
     */
    public void writeAttribute(long name, int nameLen, LocalDate date) throws IOException {
        checkSize(nameLen + 20);

        int pos = this.buffer.position();
        UNSAFE.putByte(this.address + pos++, SPACE);
        UNSAFE.copyMemory(name, this.address + pos, nameLen);
        pos += nameLen;
        UNSAFE.putByte(this.address + pos++, EQUALS);
        UNSAFE.putByte(this.address + pos++, DOUBLE_QUOTE);
        pos = raw(date, pos);
        UNSAFE.putByte(this.address + pos++, DOUBLE_QUOTE);
        this.buffer.position(pos);
    }

    /**
     * {@inheritDoc}
     */
    public void writeAttribute(long name, int nameLen, OffsetDateTime offsetDateTime) throws IOException {
        checkSize(nameLen + 30);

        int pos = this.buffer.position();
        UNSAFE.putByte(this.address + pos++, SPACE);
        UNSAFE.copyMemory(name, this.address + pos, nameLen);
        pos += nameLen;
        UNSAFE.putByte(this.address + pos++, EQUALS);
        UNSAFE.putByte(this.address + pos++, DOUBLE_QUOTE);
        pos = raw(offsetDateTime, pos);
        UNSAFE.putByte(this.address + pos++, DOUBLE_QUOTE);
        this.buffer.position(pos);
    }

    /**
     * {@inheritDoc}
     */
    public void write(char[] text, int off, int len) throws IOException {
        checkSize(text.length << 2);

        int pos = this.buffer.position();

        if (hasElements[elem]) {
            hasElements[elem] = false;
            UNSAFE.putByte(this.address + pos++, END_TAG);
        }

        this.buffer.position(raw(text, off, len, pos));

    }

    /**
     * {@inheritDoc}
     */
    public void write(char[] text) throws IOException {
        write(text, 0, text.length);
    }

    private int raw(LocalDate date, int pos) {
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        UNSAFE.putByte(this.address + pos++, (byte) (48 + (year / 1000)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (year / 100 % 10)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (year / 10 % 10)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (year % 10)));
        UNSAFE.putByte(this.address + pos++, CHAR_DASH);
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (month / 10)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (month % 10)));
        UNSAFE.putByte(this.address + pos++, CHAR_DASH);
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (day / 10)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (day % 10)));

        return pos;
    }

    private int raw(OffsetDateTime dateTime, int pos) {
        int year = dateTime.getYear();
        int month = dateTime.getMonthValue();
        int day = dateTime.getDayOfMonth();

        UNSAFE.putByte(this.address + pos++, (byte) (48 + (year / 1000)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (year / 100 % 10)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (year / 10 % 10)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (year % 10)));
        UNSAFE.putByte(this.address + pos++, CHAR_DASH);
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (month / 10)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (month % 10)));
        UNSAFE.putByte(this.address + pos++, CHAR_DASH);
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (day / 10)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (day % 10)));
        UNSAFE.putByte(this.address + pos++, CHAR_T);
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (dateTime.getHour() / 10)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (dateTime.getHour() % 10)));
        UNSAFE.putByte(this.address + pos++, CHAR_COLON);
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (dateTime.getMinute() / 10)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (dateTime.getMinute() % 10)));
        UNSAFE.putByte(this.address + pos++, CHAR_COLON);
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (dateTime.getSecond() / 10)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (dateTime.getSecond() % 10)));

        if (dateTime.getOffset().getTotalSeconds() != 0) {
            char[] offset = UnsafeString.getChars(dateTime.getOffset().toString());
            return raw(offset, 0, offset.length, pos);
        }

        return pos;
    }

    private int raw(char[] text, int off, int len, int pos) {
        char c;
        long address = this.address + pos;
        for (int i = off, n = off + len; i < n; i++) {
            c = text[i];
            // ascii
            if (c < 0x80) {
                switch (c) {
                    case '&':
                        UNSAFE.copyMemory(ADDRESS_ENTITY_AMP, address, 5);
                        address += 5;
                        break;
                    case '<':
                        UNSAFE.copyMemory(ADDRESS_ENTITY_LT, address, 4);
                        address += 4;
                        break;
                    case '>':
                        UNSAFE.copyMemory(ADDRESS_ENTITY_GT, address, 4);
                        address += 4;
                        break;
                    case '"':
                        UNSAFE.copyMemory(ADDRESS_ENTITY_QUOT, address, 6);
                        address += 6;
                        break;
                    default:
                        UNSAFE.putByte(address++, (byte) c);
                }
            } else {
                // 2-byte
                if (c < 0x800) {
                    UNSAFE.putByte(address++, (byte) (0xc0 | (c >> 6)));
                    UNSAFE.putByte(address++, (byte) (0x80 | (c & 0x3f)));
                    // 3 bytes
                } else if (c <= 0xFFFF) {
                    UNSAFE.putByte(address++, (byte) (0xe0 | (c >> 12)));
                    UNSAFE.putByte(address++, (byte) (0x80 | ((c >> 6) & 0x3f)));
                    UNSAFE.putByte(address++, (byte) (0x80 | (c & 0x3f)));
                } else {
                    // 4 bytes
                    if (c > 0x10FFFF) {
                        // illegal, as per RFC 3629
                        throw new IllegalStateException();
                    }
                    UNSAFE.putByte(address++, (byte) (0xf0 | (c >> 18)));
                    UNSAFE.putByte(address++, (byte) (0x80 | ((c >> 12) & 0x3f)));
                    UNSAFE.putByte(address++, (byte) (0x80 | ((c >> 6) & 0x3f)));
                    UNSAFE.putByte(address++, (byte) (0x80 | (c & 0x3f)));
                }
            }
        }
        return (int) (address - this.address);
    }

    /**
     * @param limit
     */
    private void checkSize(int limit) throws IOException {
        if (this.buffer.remaining() < limit) {
            this.buffer.flip();
            this.channel.write(this.buffer);
            this.buffer.clear();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void write(long address, int len) throws IOException {
        checkSize(len << 2);
        int pos = this.buffer.position();
        if (hasElements[elem]) {
            hasElements[elem] = false;
            UNSAFE.putByte(this.address + pos++, END_TAG);
        }

        UNSAFE.copyMemory(address, this.address + pos, len);
        this.buffer.position(pos + len);
    }

    /**
     * {@inheritDoc}
     */
    public void write(long address, int len, Externalizable value) throws IOException {
        checkSize(len << 2);
        int pos = this.buffer.position();
        if (hasElements[elem]) {
            hasElements[elem] = false;
            UNSAFE.putByte(this.address + pos++, END_TAG);
        }

        UNSAFE.putByte(this.address + pos++, START_TAG);
        UNSAFE.copyMemory(address, this.address + pos, len);
        pos += len;
        UNSAFE.putByte(this.address + pos++, END_TAG);
        this.buffer.position(pos);

        value.write(this);

        checkSize(len << 2);

        pos = this.buffer.position();
        UNSAFE.copyMemory(ADDRESS_ARRAY_END, this.address + pos, 2);
        pos += 2;

        UNSAFE.copyMemory(address, this.address + pos, len);
        pos += len;
        UNSAFE.putByte(this.address + pos++, END_TAG);
        this.buffer.position(pos);
    }

    /**
     * {@inheritDoc}
     */
    public void write(LocalDateTime dateTime) throws IOException {
        checkSize(32);

        int pos = this.buffer.position();
        if (hasElements[elem]) {
            hasElements[elem] = false;
            UNSAFE.putByte(this.address + pos++, END_TAG);
        }

        int year = dateTime.getYear();
        int month = dateTime.getMonthValue();
        int day = dateTime.getDayOfMonth();
        int hour = dateTime.getHour();
        int minute = dateTime.getMinute();
        int second = dateTime.getSecond();

        UNSAFE.putByte(this.address + pos++, (byte) (48 + (year / 1000)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (year / 100 % 10)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (year / 10 % 10)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (year % 10)));
        UNSAFE.putByte(this.address + pos++, CHAR_DASH);
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (month / 10)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (month % 10)));
        UNSAFE.putByte(this.address + pos++, CHAR_DASH);
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (day / 10)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (day % 10)));
        UNSAFE.putByte(this.address + pos++, CHAR_T);
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (hour / 10)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (hour % 10)));
        UNSAFE.putByte(this.address + pos++, CHAR_COLON);
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (minute / 10)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (minute % 10)));
        UNSAFE.putByte(this.address + pos++, CHAR_COLON);
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (second / 10)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (second % 10)));
        this.buffer.position(pos);
    }

    public void write(LocalTime dateTime) throws IOException {
        int pos = this.buffer.position();
        if (hasElements[elem]) {
            hasElements[elem] = false;
            UNSAFE.putByte(this.address + pos++, END_TAG);
        }

        int hour = dateTime.getHour();
        int minute = dateTime.getMinute();
        int second = dateTime.getSecond();

        UNSAFE.putByte(this.address + pos++, (byte) (48 + (hour / 10)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (hour % 10)));
        UNSAFE.putByte(this.address + pos++, CHAR_COLON);
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (minute / 10)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (minute % 10)));
        UNSAFE.putByte(this.address + pos++, CHAR_COLON);
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (second / 10)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (second % 10)));
        this.buffer.position(pos);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeStart(long address, int len) throws IOException {
        checkSize(len << 2);
        long adr = this.address + this.buffer.position();
        if (hasElements[elem]) {
            hasElements[elem] = false;
            UNSAFE.putByte(adr++, END_TAG);
        }
        UNSAFE.putByte(adr++, START_TAG);

        if (this.namespacesPtr >= 0) {
            long nsAdr = this.namespaces[namespacesPtr];
            long nsLen = this.namespacesSize[namespacesPtr];
            UNSAFE.copyMemory(nsAdr, adr, nsLen);
            adr += nsLen;
            UNSAFE.putByte(adr++, CHAR_COLON);
        }

        UNSAFE.copyMemory(address, adr, len);
        this.buffer.position((int) (adr - this.address) + len);
        hasElements[++elem] = true;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeEnd(long address, int len) throws IOException {
        checkSize(len << 2);
        if (!hasElements[elem--]) {
            long adr = this.address + this.buffer.position();
            UNSAFE.copyMemory(ADDRESS_ARRAY_END, adr, 2);
            adr += 2;

            if (this.namespacesPtr >= 0) {
                long nsAdr = this.namespaces[namespacesPtr];
                long nsLen = this.namespacesSize[namespacesPtr];
                UNSAFE.copyMemory(nsAdr, adr, nsLen);
                adr += nsLen;
                UNSAFE.putByte(adr++, CHAR_COLON);
            }
            UNSAFE.copyMemory(address, adr, len);
            UNSAFE.putByte(adr + len, END_TAG);
            this.buffer.position(((int) (adr - this.address)) + len + 1);
        } else {
            int pos = this.buffer.position();
            UNSAFE.copyMemory(ADDRESS_END_SINGLE_TAG, this.address + pos, 2);
            this.buffer.position(pos + 2);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void write(LocalDate date) throws IOException {
        checkSize(16);

        int pos = this.buffer.position();
        if (hasElements[elem]) {
            hasElements[elem] = false;
            UNSAFE.putByte(this.address + pos++, END_TAG);
        }

        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        UNSAFE.putByte(this.address + pos++, (byte) (48 + (year / 1000)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (year / 100 % 10)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (year / 10 % 10)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (year % 10)));
        UNSAFE.putByte(this.address + pos++, CHAR_DASH);
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (month / 10)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (month % 10)));
        UNSAFE.putByte(this.address + pos++, CHAR_DASH);
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (day / 10)));
        UNSAFE.putByte(this.address + pos++, (byte) (48 + (day % 10)));
        this.buffer.position(pos);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IOException
     */
    @Override
    public void writeNamespace(long key, int keyLen, long value, int valueLen) throws IOException {

        checkSize(keyLen + valueLen + 4);

        int pos = this.buffer.position();
        UNSAFE.putByte(this.address + pos++, SPACE);

        UNSAFE.copyMemory(key, this.address + pos, keyLen);
        pos += keyLen;
        UNSAFE.putByte(this.address + pos++, EQUALS);
        UNSAFE.putByte(this.address + pos++, DOUBLE_QUOTE);
        UNSAFE.copyMemory(value, this.address + pos, valueLen);
        pos += valueLen;
        UNSAFE.putByte(this.address + pos++, DOUBLE_QUOTE);
        this.buffer.position(pos);
    }

    public static byte[] getPiStart() {
        return PI_START.clone();
    }

    public static byte[] getPiEnd() {
        return PI_END.clone();
    }

    /**
     * {@inheritDoc}
     */
    public void pushNS(long addr, long size) {
        namespacesPtr++;
        this.namespaces[namespacesPtr] = addr;
        this.namespacesSize[namespacesPtr] = size;
    }

    /**
     * {@inheritDoc}
     */
    public void popNS() {
        this.namespacesPtr--;
    }


    protected void reset() {
        this.channel = null;
        this.elem = 0;
        this.namespacesPtr = -1;
        this.permissive = false;
        this.exceptions = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPermissive(boolean value) {
        this.permissive = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPermissive() {
        return permissive;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addExpcetion(LegalDocMlException exception) {
        if (this.exceptions == null) {
            this.exceptions = new ArrayList<>(8);
        }
        this.exceptions.add(exception);
    }

    public List<LegalDocMlException> getExceptions() {
        return exceptions;
    }
}

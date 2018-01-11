package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.container.InlineCMContainer;
import io.legaldocml.akn.group.ANinline;
import io.legaldocml.akn.group.ANmarker;
import io.legaldocml.akn.group.ANsemanticInline;
import io.legaldocml.akn.group.ANtitleInline;
import io.legaldocml.akn.group.HTMLinline;
import io.legaldocml.akn.group.InlineCM;
import io.legaldocml.akn.group.SubFlowElements;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import javax.xml.stream.XMLStreamConstants;
import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.element.Groups.convert;
import static io.legaldocml.akn.element.Groups.inlineCM;
import static java.util.Objects.requireNonNull;

/**
 * The complex type inline defines the content model and attributes shared by all blocks and inlines. Here the eId
 * attribute is optional.
 * <p>
 * <pre>
 *   <xsd:complexType name="inline" mixed="true">
 *     <xsd:choice minOccurs="0" maxOccurs="unbounded">
 *       <xsd:group ref="InlineCM"/>
 *     <xsd:choice>
 *     <xsd:attributeGroup ref="coreopt"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class InlineTypeAbstract extends AbstractCore implements InlineCMContainer {

    /**
     * Container for all data fors this inline.
     */
    private final AknList<InlineCM> data = new AknList<>(new InlineCM[8]);

    private static final ImmutableMap<String, Supplier<InlineCM>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<InlineCM>>builder()
                .putAll(convert(inlineCM()))
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final InlineCM remove(int index) {
        return this.data.remove(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(InlineCM inlineCM) {
        this.data.add(requireNonNull(inlineCM));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(SubFlowElements subFlowElements) {
        this.data.add(requireNonNull(subFlowElements));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(ANtitleInline titleInline) {
        this.data.add(requireNonNull(titleInline));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(HTMLinline htmLinline) {
        this.data.add(requireNonNull(htmLinline));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(ANinline inline) {
        this.data.add(requireNonNull(inline));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(ANmarker marker) {
        this.data.add(requireNonNull(marker));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(ANsemanticInline inline) {
        this.data.add(requireNonNull(inline));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        read(reader, this.data);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        this.data.write(writer);
    }

    protected final AknList<InlineCM> getData() {
        return data;
    }

    static void read(XmlReader reader, AknList<InlineCM> data) {
        InlineCM inlineCM;

        QName qName = reader.getQName();
        int eventType;

        while (true) {
            eventType = reader.next();
            if (eventType == XMLStreamConstants.START_ELEMENT) {
                Supplier<InlineCM> inlineCMSupplier = ELEMS.get(reader.getQName().getLocalName());
                if (inlineCMSupplier == null) {
                    throw new RuntimeException(qName + " --> [" + reader.getQName() + "]");
                }
                inlineCM = inlineCMSupplier.get();
                inlineCM.read(reader);
                data.add(inlineCM);
                continue;
            }
            if (eventType == XMLStreamConstants.CHARACTERS) {
                char[] content = reader.getText().value();
                if (content != null && content.length > 0) {
                    data.add(new StringInlineCM(content));
                }
                continue;
            }
            if (eventType == XMLStreamConstants.PROCESSING_INSTRUCTION) {
                // TODO
                continue;
            }
            if (eventType == XMLStreamConstants.END_ELEMENT && qName.equals(reader.getQName())) {
                break;
            }
        }
    }

}
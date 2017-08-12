package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Source;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static io.legaldocml.akn.element.Attributes.biConsumerAgentRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writeSource;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * <pre>
 *   &lt;xsd:element name="mappings"&gt;
 *     &lt;xsd:complexType&gt;
 *       &lt;xsd:choice minOccurs="1" maxOccurs="unbounded"&gt;
 *         &lt;xsd:element ref="implicitReference"/&gt;
 *         &lt;xsd:element ref="alternativeReference"/&gt;
 *       &lt;xsd:choice&gt;
 *       &lt;xsd:attributeGroup ref="source"/&gt;
 *     &lt;xsd:complexType&gt;
 *   &lt;xsd:element&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class OtherReferences implements Source {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "OtherReferences";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    private static final ImmutableMap<String, Supplier<OtherReferencesElement>> ELEMS;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .put(Source.ATTRIBUTE, biConsumerAgentRef(getFieldOffset(OtherReferences.class, "source")))
                .build();

        ELEMS = ImmutableMap.<String, Supplier<OtherReferencesElement>>builder()
                .put(AlternativeReference.ELEMENT, AlternativeReference::new)
                .put(ImplicitReference.ELEMENT, ImplicitReference::new)
                .build();
    }

    // Mandatory (min 1)
    private final AknList<OtherReferencesElement> elems = new AknList<>(new OtherReferencesElement[2]);

    // Mandatory
    private AgentRef source;

    /**
     * {@inheritDoc}
     */
    @Override
    public AgentRef getSource() {
        return this.source;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSource(AgentRef source) {
        this.source = source;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 5);
        writeSource(writer, this);
        this.elems.write(writer);
        writer.writeEnd(ADDRESS, 5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        Attributes.read(reader, this);
        reader.nextStartOrEndElement();
        XmlReaderHelper.read(reader, this.elems, ELEMS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }

}
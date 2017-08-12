package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Source;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerAgentRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writeSource;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * <pre>
 *   &lt;xsd:element name="mappings"&gt;
 *     &lt;xsd:complexType&gt;
 *       &lt;xsd:sequence minOccurs="1" maxOccurs="unbounded"&gt;
 *         &lt;xsd:element ref="mapping"/&gt;
 *       &lt;xsd:sequence&gt;
 *       &lt;xsd:attributeGroup ref="source"/&gt;
 *     &lt;xsd:complexType&gt;
 *   &lt;xsd:element&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Mappings implements Source {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "mappings";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .put(Source.ATTRIBUTE, biConsumerAgentRef(getFieldOffset(Mappings.class, "source")))
                .build();
    }

    // Mandatory (min 1)
    private final AknList<Mapping> elements = new AknList<>(new Mapping[4]);

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
        this.elements.write(writer);
        writer.writeEnd(ADDRESS, 5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        Attributes.read(reader, this);
        reader.nextStartOrEndElement();

        if (reader.getQName().equalsLocalName(Mapping.ELEMENT)) {
            Mapping mapping;
            do {
                mapping = new Mapping();
                mapping.read(reader);
                this.elements.add(mapping);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(Mapping.ELEMENT));
        }
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
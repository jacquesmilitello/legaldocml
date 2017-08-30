package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.attribute.Source;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerAgentRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writeSource;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * <pre>
 *   <xsd:element name="mappings">
 *     <xsd:complexType>
 *       <xsd:sequence minOccurs="1" maxOccurs="unbounded">
 *         <xsd:element ref="mapping"/>
 *       <xsd:sequence>
 *       <xsd:attributeGroup ref="source"/>
 *     <xsd:complexType>
 *   <xsd:element>
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

    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .put(AknAttributes.SOURCE, biConsumerAgentRef(getFieldOffset(Mappings.class, "source")))
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
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }

}
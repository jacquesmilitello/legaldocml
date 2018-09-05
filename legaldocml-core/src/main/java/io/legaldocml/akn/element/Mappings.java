package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Source;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.SOURCE;
import static io.legaldocml.akn.AknElements.MAPPING;
import static io.legaldocml.akn.AknElements.MAPPINGS;
import static io.legaldocml.akn.element.Attributes.ATTRIBUTE_CONSUMER;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4AgentRef;
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
     * Memory address.
     */
    private static final long ADDRESS_MAPPINGS = Buffers.address(MAPPINGS);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .put(SOURCE, attributeGetterSetter4AgentRef(SOURCE, getFieldOffset(Mappings.class, "source")))
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
        writer.writeStart(ADDRESS_MAPPINGS, 5);
        writeSource(writer, this);
        this.elements.write(writer);
        writer.writeEnd(ADDRESS_MAPPINGS, 5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        reader.forEach(this, ATTRIBUTE_CONSUMER);
        reader.nextStartOrEndElement();

        if (reader.getQName().equalsLocalName(MAPPING)) {
            Mapping mapping;
            do {
                mapping = new Mapping();
                mapping.read(reader);
                this.elements.add(mapping);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(MAPPING));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return MAPPINGS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }

}
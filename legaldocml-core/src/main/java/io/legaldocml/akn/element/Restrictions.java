package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.attribute.Source;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static io.legaldocml.akn.element.Attributes.biConsumerAgentRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writeSource;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * <pre>
 *   <xsd:element name="restrictions">
 *     <xsd:complexType>
 *       <xsd:sequence minOccurs="1" maxOccurs="unbounded">
 *         <xsd:element ref="restriction"/>
 *       <xsd:sequence>
 *       <xsd:attributeGroup ref="source"/>
 *     <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Restrictions implements Source {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "restrictions";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    private static final ImmutableMap<String, Supplier<Restriction>> ELEMS;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .put(AknAttributes.SOURCE, biConsumerAgentRef(getFieldOffset(Restrictions.class, "source")))
                .build();

        ELEMS = ImmutableMap.<String, Supplier<Restriction>>builder()
                .put(Restriction.ELEMENT, Restriction::new)
                .build();
    }

    // Mandatory (min 1)
    private final AknList<Restriction> elems = new AknList<>(new Restriction[2]);

    // Mandatory
    private AgentRef source;

    /**
     * {@inheritDoc}
     */
    public AgentRef getSource() {
        return this.source;
    }

    /**
     * {@inheritDoc}
     */
    public void setSource(AgentRef source) {
        this.source = source;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 12);
        writeSource(writer, this);
        this.elems.write(writer);
        writer.writeEnd(ADDRESS, 12);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        Attributes.read(reader, this);
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
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }
}
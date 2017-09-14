package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.attribute.Name;
import io.legaldocml.akn.group.HierElements;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.AknElements.H_CONTAINER;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.util.XmlWriterHelper.writeName;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element hcontainer is a generic element for a hierarchical container. It can be placed in a hierarchy instead of
 * any of the other hierarchical containers. The attribute name is required and gives a name to the element.
 *
 * <pre>
 *   <xsd:element name="hcontainer">
 * 	   <xsd:complexType>
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="hierarchy">
 * 		     <xsd:attributeGroup ref="name"/>
 * 		   <xsd:extension>
 * 	     <xsd:complexContent>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Hcontainer extends Hierarchy implements Name, HierElements {

    /**
     * Memory address.
     */
    private static final long ADDRESS_H_CONTAINER = Buffers.address(H_CONTAINER);

    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(Hierarchy.ATTRIBUTES)
                .put("name", biConsumerString(getFieldOffset(Hcontainer.class, "name")))
                .build();
    }

    /**
     * The attribute name.
     */
    private String name;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_H_CONTAINER, 10);
        writeName(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_H_CONTAINER, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return H_CONTAINER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }

}
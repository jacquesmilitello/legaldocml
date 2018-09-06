package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Name;
import io.legaldocml.akn.group.HierElements;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.NAME;
import static io.legaldocml.akn.AknElements.H_CONTAINER;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element hcontainer is a generic element for a hierarchical container. It can be placed in a hierarchy instead of
 * any of the other hierarchical containers. The attribute name is required and gives a name to the element.
 * <p>
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

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(Hierarchy.ATTRIBUTES)
                .put(NAME, attributeGetterSetter4String(NAME, getFieldOffset(Hcontainer.class, "name")))
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
        Name.super.write(writer);
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
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            super.accept(visitor);
            visitor.visitLeave(this);
        }
    }

}
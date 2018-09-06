package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Name;
import io.legaldocml.akn.group.ANsemanticInline;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.NAME;
import static io.legaldocml.akn.AknElements.ENTITY;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * the element entity is a generic inline element to identify a text fragment introducing or referring to a concept in
 * the ontology.
 * <p>
 * <pre>
 * 	<xsd:element name="entity">
 * 		<xsd:complexType mixed="true">
 * 			<xsd:complexContent>
 * 				<xsd:extension base="inlinereqreq">
 * 					<xsd:attributeGroup ref="name"/>
 * 				<xsd:extension>
 * 			<xsd:complexContent>
 * 		<xsd:complexType>
 * 	<xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Entity extends InlineReqReqType implements Name, ANsemanticInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_ENTITY = Buffers.address(ENTITY);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(Hierarchy.ATTRIBUTES)
                .put(NAME, attributeGetterSetter4String(NAME, getFieldOffset(Entity.class, "name")))
                .build();
    }

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
        writer.writeStart(ADDRESS_ENTITY, 6);
        Name.super.write(writer);
        super.write(writer);
        writer.writeEnd(ADDRESS_ENTITY, 6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ENTITY;
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
package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.RefersOpt;
import io.legaldocml.akn.type.ListReferenceRef;
import io.legaldocml.akn.type.RestrictionType;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.REFERS_TO;
import static io.legaldocml.akn.AknAttributes.TYPE;
import static io.legaldocml.akn.AknElements.RESTRICTION;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4Enum;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4ListReferenceRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRefers;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRestrictionType;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element restriction specifies information about a restriction (such as a jurisdiction specification) by pointing
 * to a specific legislative, geographic or temporal events through the refersTo attribute.
 * <p>
 * <pre>
 *   <xsd:element name="restriction" type="anyOtherType">
 * 	   <xsd:complexType>
 *       <xsd:complexContent>
 *         <xsd:extension base="anyOtherType">
 *           <xsd:attributeGroup ref="refers"/>
 *           <xsd:attributeGroup ref="restrictionType"/>
 *         <xsd:extension>
 *       <xsd:complexContent>
 *     <xsd:complexType>
 *   <xsd:element>
 * <pre>
 *
 *     @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Restriction extends AnyOtherType implements RefersOpt, io.legaldocml.akn.attribute.RestrictionType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_RESTRICTION = Buffers.address(RESTRICTION);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(AnyOtherType.ATTRIBUTES)
                .put(REFERS_TO, attributeGetterSetter4ListReferenceRef(REFERS_TO, getFieldOffset(Restriction.class, "refersTo")))
                .put(TYPE, attributeGetterSetter4Enum(TYPE, getFieldOffset(Restriction.class, "refersTo"), RestrictionType.class))
                .build();
    }

    private RestrictionType type;
    private ListReferenceRef refersTo;

    /**
     * {@inheritDoc}
     */
    @Override
    public RestrictionType getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setType(RestrictionType type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListReferenceRef getRefersTo() {
        return this.refersTo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRefersTo(ListReferenceRef refersTo) {
        this.refersTo = refersTo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_RESTRICTION, 11);
        writeRefers(writer, this);
        writeRestrictionType(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_RESTRICTION, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return RESTRICTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }

}
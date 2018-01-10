package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.RangeReq;
import io.legaldocml.akn.group.ANinline;
import io.legaldocml.akn.type.EidRef;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.FROM;
import static io.legaldocml.akn.AknAttributes.UP_TO;
import static io.legaldocml.akn.AknElements.RREF;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4EidRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRange;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * the element rref is an inline element containing a range of references between the IRI specified in the from
 * attribute and the one specified in the upTo attribute.
 *
 * <pre>
 *   <xsd:element name="rref">
 *     <xsd:complexType mixed="true">
 * 	     <xsd:complexContent>
 * 	  	   <xsd:extension base="inlinereq">
 * 		     <xsd:attributeGroup ref="range"/>
 * 		   <xsd:extension>
 * 	     <xsd:complexContent>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Rref extends InlineReqType implements RangeReq, ANinline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_RREF = Buffers.address(RREF);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(InlineReqType.ATTRIBUTES)
                .put(FROM, attributeGetterSetter4EidRef(FROM, getFieldOffset(Rref.class, "from")))
                .put(UP_TO, attributeGetterSetter4EidRef(UP_TO, getFieldOffset(Rref.class, "upTo")))
                .build();
    }

    private EidRef from;
    private EidRef upTo;

    /**
     * {@inheritDoc}
     */
    @Override
    public EidRef getFrom() {
        return from;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFrom(EidRef from) {
        this.from = from;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EidRef getUpTo() {
        return upTo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUpTo(EidRef upTo) {
        this.upTo = upTo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_RREF, 4);
        writeRange(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_RREF, 4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return RREF;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }
}

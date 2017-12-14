package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.NormalizedAtt;
import io.legaldocml.akn.group.ANsemanticInline;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.NORMALIZED;
import static io.legaldocml.akn.AknElements.QUANTITY;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.akn.util.XmlWriterHelper.writeNormalizedAtt;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element quantity is an inline element to identify a text fragment introducing or referring to a quantity. This
 * could be a dimensionless number, or a number referring to a length, weight, duration, etc. or even a sum of money.
 * The attribute normalized contains the value normalized in a number, if appropriate.
 * <p>
 * <pre>
 *   <xsd:element name="quantity">
 * 	   <xsd:complexType mixed="true">
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="inlinereqreq">
 * 		     <xsd:attributeGroup ref="normalizedAtt"/>
 * 		   <xsd:extension>
 * 	     <xsd:complexContent>
 *     <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Quantity extends InlineReqReqType implements NormalizedAtt, ANsemanticInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_QUANTITY = Buffers.address(QUANTITY);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(InlineReqReqType.ATTRIBUTES)
                .put(NORMALIZED, attributeGetterSetter4String(NORMALIZED, getFieldOffset(Quantity.class, "normalized")))
                .build();
    }

    private String normalized;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNormalized() {
        return this.normalized;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNormalized(String normalized) {
        this.normalized = normalized;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_QUANTITY, 8);
        writeNormalizedAtt(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_QUANTITY, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return QUANTITY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }

}
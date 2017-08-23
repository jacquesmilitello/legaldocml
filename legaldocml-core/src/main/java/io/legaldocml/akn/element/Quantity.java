package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.NormalizedAtt;
import io.legaldocml.akn.group.ANsemanticInline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.util.XmlWriterHelper.writeNormalizedAtt;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element quantity is an inline element to identify a text fragment introducing or referring to a quantity. This
 * could be a dimensionless number, or a number referring to a length, weight, duration, etc. or even a sum of money.
 * The attribute normalized contains the value normalized in a number, if appropriate.
 *
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
     * XML tag element name.
     */
    public static final String ELEMENT = "quantity";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(InlineReqReqType.ATTRIBUTES)
                .put(NormalizedAtt.ATTRIBUTE, biConsumerString(getFieldOffset(Quantity.class, "normalized")))
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
        writer.writeStart(ADDRESS, 8);
        writeNormalizedAtt(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS, 8);
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
package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Number;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;
import static io.legaldocml.unsafe.UnsafeString.getChars;

/**
 * the complex type eolType is shared by eol and eop elements as being able to specify a hyphen character and a position
 * within the next word in which the break can happen, and the number if any, associated to the page or line at issue.
 *
 * <pre>
 *   <xsd:complexType name="eolType">
 * 	   <xsd:complexContent>
 * 	     <xsd:extension base="markeropt">
 * 	       <xsd:attributeGroup ref="number"/>
 * 		   <xsd:attribute name="breakat" type="xsd:integer" />
 * 	   	   <xsd:attribute name="breakWith" type="xsd:string"/>
 * 	     <xsd:extension>
 *     <xsd:complexContent>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class EolType extends MarkerOpt implements Number {

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(MarkerOpt.ATTRIBUTES)
                .put("breakat", Attributes.biConsumerInteger(getFieldOffset(EolType.class, "breakat")))
                .put("breakWith", Attributes.biConsumerInteger(getFieldOffset(EolType.class, "breakWith")))
                .put(Number.ATTRIBUTE, Attributes.biConsumerInteger(getFieldOffset(EolType.class, "number")))
                .build();
    }

    private Integer breakat;
    private String breakWith;
    private String number;

    public Integer getBreakat() {
        return breakat;
    }

    public void setBreakat(Integer breakat) {
        this.breakat = breakat;
    }

    public String getBreakWith() {
        return this.breakWith;
    }

    public void setBreakWith(String breakWith) {
        this.breakWith = breakWith;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNumber() {
        return this.number;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        if (this.breakat != null) {
            writer.writeAttribute(Attributes.ADDRESS_BREAKAT, 7, getChars(this.breakat.toString()));
        }
        super.write(writer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
       // only attributes -> skip
    }
}
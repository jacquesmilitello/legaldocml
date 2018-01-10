package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.group.ANsemanticInline;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;
import java.time.OffsetDateTime;

import static io.legaldocml.akn.AknAttributes.DATE;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4DateTime;
import static io.legaldocml.akn.util.XmlWriterHelper.writeDate;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element date is an inline element to identify a date expressed in the text and to propose a normalized
 * representation in the date attribute.
 * <p>
 * <pre>
 *   <xsd:element name="date">
 * 	   <xsd:complexType mixed="true">
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="inline">
 * 		     <xsd:attributeGroup ref="date"/>
 * 		   <xsd:extension>
 * 	     <xsd:complexContent>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Date extends InlineType implements io.legaldocml.akn.attribute.Date, ANsemanticInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DATE = Buffers.address(DATE);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(InlineType.ATTRIBUTES)
                .put(DATE, attributeGetterSetter4DateTime(DATE, getFieldOffset(Date.class, "date")))
                .build();
    }

    // Mandatory
    private OffsetDateTime date;

    /**
     * {@inheritDoc}
     */
    @Override
    public OffsetDateTime getDate() {
        return this.date;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DATE, 4);
        writeDate(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_DATE, 4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }

}
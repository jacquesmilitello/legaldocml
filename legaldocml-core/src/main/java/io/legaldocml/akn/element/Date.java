package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.group.ANsemanticInline;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerDateTime;
import static io.legaldocml.akn.util.XmlWriterHelper.writeDate;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element date is an inline element to identify a date expressed in the text and to propose a normalized
 * representation in the date attribute.
 *
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
     * XML Tag element name.
     */
    public static final String ELEMENT = "date";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(InlineType.ATTRIBUTES)
                .put(AknAttributes.DATE, biConsumerDateTime(getFieldOffset(Date.class, "date")))
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
        writer.writeStart(ADDRESS, 4);
        writeDate(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS, 4);
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
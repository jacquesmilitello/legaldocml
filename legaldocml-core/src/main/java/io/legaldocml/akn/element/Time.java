package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.group.ANsemanticInline;
import io.legaldocml.util.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.AknElements.TIME;
import static io.legaldocml.akn.util.XmlWriterHelper.writeTime;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element time is an inline element to identify a time expressed in the text and to propose a normalized
 * representation in the time attribute.
 *
 * <pre>
 *   <xsd:element name="time">
 * 	   <xsd:complexType mixed="true">
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="inline">
 * 		     <xsd:attributeGroup ref="time"/>
 * 		   <xsd:extension>
 * 		 <xsd:complexContent>
 *     <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Time extends InlineType implements io.legaldocml.akn.attribute.Time, ANsemanticInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_TIME = Buffers.address(TIME);

    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(InlineType.ATTRIBUTES)
                .put(AknAttributes.TIME, Attributes.biConsumerDateTime(getFieldOffset(Time.class, "time")))
                .build();
    }

    // Mandatory
    private OffsetDateTime time;

    /**
     * {@inheritDoc}
     */
    @Override
    public OffsetDateTime getTime() {
        return this.time;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTime(OffsetDateTime time) {
        this.time = time;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_TIME, 4);
        writeTime(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_TIME, 4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return TIME;
    }

    @Override
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }

}
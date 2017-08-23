package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.RecordedTimeType;
import io.legaldocml.akn.attribute.Time;
import io.legaldocml.akn.group.ANinline;
import io.legaldocml.akn.type.TimeType;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.ADDRESS_TYPE;
import static io.legaldocml.akn.element.Attributes.biConsumerDateTime;
import static io.legaldocml.akn.element.Attributes.biConsumerEnum;
import static io.legaldocml.akn.util.XmlWriterHelper.writeTime;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;
import static io.legaldocml.unsafe.UnsafeString.getChars;

/**
 * the element recordedTime is an inline element for the specification of an explicit mention of a time (e.g., in a
 * debate).
 *
 * <pre>
 *   <xsd:element name="recordedTime">
 *     <xsd:complexType mixed="true">
 * 	     <xsd:complexContent>
 * 	 	   <xsd:extension base="inline">
 * 		     <xsd:attributeGroup ref="recordedTimeType"/>
 *           <xsd:attributeGroup ref="time"/>
 * 		   <xsd:extension>
 * 	     <xsd:complexContent>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class RecordedTime extends InlineType implements Time, RecordedTimeType, ANinline {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "recordedTime";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    protected static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {

        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(ModificationType.ATTRIBUTES)
                .put(RecordedTimeType.ATTRIBUTE, biConsumerEnum(getFieldOffset(RecordedTime.class, "type"), TimeType.class))
                .put(Time.ATTRIBUTE, biConsumerDateTime(getFieldOffset(RecordedTime.class, "time")))
                .build();
    }

    private TimeType type;
    private OffsetDateTime time;

    /**
     * {@inheritDoc}
     */
    @Override
    public TimeType getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setType(TimeType type) {
        this.type = type;
    }

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
        writer.writeStart(ADDRESS, 12);
        if (this.type != null) {
            writer.writeAttribute(ADDRESS_TYPE, 4, getChars(this.type.name()));
        }
        writeTime(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS, 12);
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

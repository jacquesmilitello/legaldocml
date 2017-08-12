package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.group.ANsemanticInline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.util.XmlWriterHelper.writeTime;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element time is an inline element to identify a time expressed in the text and to propose a normalized
 * representation in the time attribute.
 *
 * <pre>
 *   &lt;xsd:element name="time"&gt;
 * 	   &lt;xsd:complexType mixed="true"&gt;
 * 	     &lt;xsd:complexContent&gt;
 * 		   &lt;xsd:extension base="inline"&gt;
 * 		     &lt;xsd:attributeGroup ref="time"/&gt;
 * 		   &lt;xsd:extension&gt;
 * 		 &lt;xsd:complexContent&gt;
 *     &lt;xsd:complexType&gt;
 *   &lt;xsd:element&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Time extends InlineType implements io.legaldocml.akn.attribute.Time, ANsemanticInline {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "time";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(InlineType.ATTRIBUTES)
                .put(io.legaldocml.akn.attribute.Time.ATTRIBUTE, Attributes.biConsumerDateTime(getFieldOffset(Time.class, "time")))
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
        writer.writeStart(ADDRESS, 4);
        writeTime(writer, this);
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

    @Override
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }

}
package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Duration;
import io.legaldocml.akn.attribute.Interval;
import io.legaldocml.akn.attribute.RefersReq;
import io.legaldocml.akn.type.EventRefRef;
import io.legaldocml.akn.type.ListReferenceRef;
import io.legaldocml.akn.type.XmlDuration;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.util.ToStringBuilder;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.DURATION;
import static io.legaldocml.akn.AknAttributes.END;
import static io.legaldocml.akn.AknAttributes.REFERS_TO;
import static io.legaldocml.akn.AknAttributes.START;
import static io.legaldocml.akn.AknElements.TIME_INTERVAL;
import static io.legaldocml.akn.element.Attributes.biConsumerEventRefRef;
import static io.legaldocml.akn.element.Attributes.biConsumerListReferenceRef;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.util.XmlWriterHelper.writeDuration;
import static io.legaldocml.akn.util.XmlWriterHelper.writeInterval;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRefersReq;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element timeInterval contains all the data needed to identify a specific time interval. It is built either with
 * two dates or with a date and a duration (exactly two of the 'start', 'end' and 'duration' attributes can be
 * specified). Values of the 'start' and 'end' attributes are NOT dates, but references to event elements in the
 * corresponding metadata section. The refers attribute is a reference to a temporal concept belonging to the Akoma
 * Ntoso ontology and specified in the references section.
 * <p>
 * <pre>
 * 	 <xsd:element name="timeInterval">
 * 	   <xsd:complexType>
 *       <xsd:complexContent>
 *         <xsd:extension base="metaopt">
 *           <xsd:attributeGroup ref="interval"/>
 *           <xsd:attributeGroup ref="duration"/>
 *           <xsd:attributeGroup ref="refersreq"/>
 *         <xsd:extension>
 *       <xsd:complexContent>
 *     <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class TimeInterval extends MetaOpt implements RefersReq, Interval, Duration {

    /**
     * Memory address.
     */
    private static final long ADDRESS_TIME_INTERVAL = Buffers.address(TIME_INTERVAL);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(MetaOpt.ATTRIBUTES)
                .put(REFERS_TO, biConsumerListReferenceRef(REFERS_TO, getFieldOffset(TimeInterval.class, "refersTo")))
                .put(START, biConsumerEventRefRef(START, getFieldOffset(TimeInterval.class, "start")))
                .put(END, biConsumerEventRefRef(END, getFieldOffset(TimeInterval.class, "end")))
                .put(DURATION, biConsumerString(DURATION, getFieldOffset(TimeInterval.class, "duration")))
                .build();
    }

    private EventRefRef start;
    private EventRefRef end;
    private XmlDuration duration;
    private ListReferenceRef refersTo;

    /**
     * {@inheritDoc}
     */
    @Override
    public EventRefRef getStart() {
        return this.start;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStart(EventRefRef start) {
        this.start = start;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EventRefRef getEnd() {
        return this.end;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEnd(EventRefRef end) {
        this.end = end;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XmlDuration getDuration() {
        return this.duration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDuration(XmlDuration duration) {
        this.duration = duration;
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
        writer.writeStart(ADDRESS_TIME_INTERVAL, 12);
        super.write(writer);
        writeRefersReq(writer, this);
        writeInterval(writer, this);
        writeDuration(writer, this);
        writer.writeEnd(ADDRESS_TIME_INTERVAL, 12);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return TIME_INTERVAL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void toString(ToStringBuilder builder) {
        builder.append(REFERS_TO, this.refersTo);
        builder.append(START, this.start);
        builder.append(END, this.end);
        builder.append(DURATION, this.duration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        visitor.visit(this);
    }

}
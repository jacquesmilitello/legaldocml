package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Date;
import io.legaldocml.akn.attribute.Originating;
import io.legaldocml.akn.attribute.RefersOpt;
import io.legaldocml.akn.attribute.Source;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.EventType;
import io.legaldocml.akn.type.ListReferenceRef;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.time.OffsetDateTime;

import static io.legaldocml.akn.AknAttributes.DATE;
import static io.legaldocml.akn.AknAttributes.ORIGINATING_EXPRESSION;
import static io.legaldocml.akn.AknAttributes.REFERS_TO;
import static io.legaldocml.akn.AknAttributes.SOURCE;
import static io.legaldocml.akn.AknAttributes.TYPE;
import static io.legaldocml.akn.AknElements.EVENT_REF;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4AgentRef;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4Boolean;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4DateTime;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4Enum;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4ListReferenceRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writeDate;
import static io.legaldocml.akn.util.XmlWriterHelper.writeEventType;
import static io.legaldocml.akn.util.XmlWriterHelper.writeOriginating;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRefers;
import static io.legaldocml.akn.util.XmlWriterHelper.writeSource;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element eventRef is a metadata element specifying facts about an event that had an effect on the document. For
 * each event, a date, a type and a document that generated the event must be referenced.
 *
 * <pre>
 *   <xsd:element name="eventRef">
 * 	   <xsd:complexType>
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="anyOtherType">
 * 		     <xsd:attributeGroup ref="date"/>
 *           <xsd:attributeGroup ref="source"/>
 *           <xsd:attributeGroup ref="refers"/>
 *           <xsd:attributeGroup ref="eventType"/>
 *           <xsd:attributeGroup ref="originating"/>
 * 		   </xsd:extension>
 * 	     </xsd:complexContent>
 * 	   </xsd:complexType>
 *   </xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class EventRef extends AnyOtherType implements Date, Source, RefersOpt, Originating, io.legaldocml.akn.attribute.EventType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_EVENT_REF = Buffers.address(EVENT_REF);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(AnyOtherType.ATTRIBUTES)
                .put(DATE, attributeGetterSetter4DateTime(DATE, getFieldOffset(EventRef.class, "date")))
                .put(SOURCE, attributeGetterSetter4AgentRef(SOURCE, getFieldOffset(EventRef.class, "source")))
                .put(REFERS_TO, attributeGetterSetter4ListReferenceRef(REFERS_TO, getFieldOffset(EventRef.class, "refersTo")))
                .put(ORIGINATING_EXPRESSION, attributeGetterSetter4Boolean(ORIGINATING_EXPRESSION, getFieldOffset(EventRef.class, "originatingExpression")))
                .put(TYPE, attributeGetterSetter4Enum(TYPE, getFieldOffset(EventRef.class, "type"), EventType.class))

                .build();
    }

    private OffsetDateTime date;
    private AgentRef source;
    private ListReferenceRef refersTo;
    private Boolean originatingExpression;
    private EventType type;

    /**
     * {@inheritDoc}
     */
    @Override
    public AgentRef getSource() {
        return this.source;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSource(AgentRef source) {
        this.source = source;
    }

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
    public EventType getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setType(EventType type) {
        this.type = type;
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
    public Boolean getOriginatingExpression() {
        return this.originatingExpression;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOriginatingExpression(Boolean originatingExpression) {
        this.originatingExpression = originatingExpression;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_EVENT_REF, 8);
        writeDate(writer, this);
        writeSource(writer, this);
        writeRefers(writer, this);
        writeOriginating(writer, this);
        writeEventType(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_EVENT_REF, 8);
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
    public String name() {
        return EVENT_REF;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            super.accept(visitor);
            visitor.visitLeave(this);
        }
    }

}
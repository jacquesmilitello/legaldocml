package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.SpeechAtts;
import io.legaldocml.akn.group.ANcontainers;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.RoleRef;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.time.LocalDateTime;

import static io.legaldocml.akn.AknAttributes.AS;
import static io.legaldocml.akn.AknAttributes.BY;
import static io.legaldocml.akn.AknAttributes.END_TIME;
import static io.legaldocml.akn.AknAttributes.START_TIME;
import static io.legaldocml.akn.AknAttributes.TO;
import static io.legaldocml.akn.AknElements.SPEECH_GROUP;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4AgentRef;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4LocalDateTime;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4RoleRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writeSpeechAtts;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element speechGroup is a container of speech elements. This element is meant to pooint out, in a complex sequence
 * of individual speech elements, the main contributor, i.e., the individual speech who was introducedand expected and
 * that is causing the complex sequence that follows. Attributes by, as and to are those of the main speech.
 * <p>
 * <pre>
 *   <xsd:element name="speechGroup">
 * 	   <xsd:complexType>
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="althierarchy">
 * 		     <xsd:attributeGroup ref="speechAtts"/>
 * 		   <xsd:extension>
 * 		 <xsd:complexContent>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class SpeechGroup extends AltHierarchy implements SpeechAtts, ANcontainers {

    /**
     * Memory address.
     */
    private static final long ADDRESS_SPEECH_GROUP = Buffers.address(SPEECH_GROUP);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(AltHierarchy.ATTRIBUTES)
                .put(AS, attributeGetterSetter4RoleRef(AS, getFieldOffset(SpeechGroup.class, "as")))
                .put(BY, attributeGetterSetter4AgentRef(BY, getFieldOffset(SpeechGroup.class, "by")))
                .put(TO, attributeGetterSetter4AgentRef(TO, getFieldOffset(SpeechGroup.class, "to")))
                .put(START_TIME, attributeGetterSetter4LocalDateTime(START_TIME, getFieldOffset(SpeechGroup.class, "startTime")))
                .put(END_TIME, attributeGetterSetter4LocalDateTime(END_TIME, getFieldOffset(SpeechGroup.class, "endTime")))
                .build();
    }

    private RoleRef as;
    private AgentRef by;
    private AgentRef to;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * {@inheritDoc}
     */
    @Override
    public RoleRef getAs() {
        return this.as;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AgentRef getBy() {
        return this.by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAs(RoleRef as) {
        this.as = as;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBy(AgentRef by) {
        this.by = by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AgentRef getTo() {
        return this.to;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTo(AgentRef to) {
        this.to = to;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_SPEECH_GROUP, 11);
        writeSpeechAtts(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_SPEECH_GROUP, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return SPEECH_GROUP;
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
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            super.accept(visitor);
            visitor.visitLeave(this);
        }
    }
}
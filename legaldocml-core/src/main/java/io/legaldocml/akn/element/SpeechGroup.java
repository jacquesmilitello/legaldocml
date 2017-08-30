package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.attribute.SpeechAtts;
import io.legaldocml.akn.group.ANcontainers;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.RoleRef;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerAgentRef;
import static io.legaldocml.akn.element.Attributes.biConsumerLocalDateTime;
import static io.legaldocml.akn.element.Attributes.biConsumerRoleRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writeSpeechAtts;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element speechGroup is a container of speech elements. This element is meant to pooint out, in a complex sequence
 * of individual speech elements, the main contributor, i.e., the individual speech who was introducedand expected and
 * that is causing the complex sequence that follows. Attributes by, as and to are those of the main speech.
 *
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
     * XML Tag element name.
     */
    public static final String ELEMENT = "speechGroup";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(AltHierarchy.ATTRIBUTES)
                .put(AknAttributes.AS, biConsumerRoleRef(getFieldOffset(SpeechGroup.class, "as")))
                .put(AknAttributes.BY, biConsumerAgentRef(getFieldOffset(SpeechGroup.class, "by")))
                .put(AknAttributes.TO, biConsumerAgentRef(getFieldOffset(SpeechGroup.class, "to")))
                .put(AknAttributes.START_TIME, biConsumerLocalDateTime(getFieldOffset(SpeechGroup.class, "startTime")))
                .put(AknAttributes.END_TIME, biConsumerLocalDateTime(getFieldOffset(SpeechGroup.class, "endTime")))
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
        writer.writeStart(ADDRESS, 11);
        writeSpeechAtts(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS, 11);
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
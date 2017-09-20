package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.attribute.Actor;
import io.legaldocml.akn.attribute.Agent;
import io.legaldocml.akn.attribute.Date;
import io.legaldocml.akn.attribute.Outcome;
import io.legaldocml.akn.attribute.Role;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.ConceptRef;
import io.legaldocml.akn.type.RoleRef;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.AknElements.STEP;
import static io.legaldocml.akn.element.Attributes.biConsumerAgentRef;
import static io.legaldocml.akn.element.Attributes.biConsumerConceptRef;
import static io.legaldocml.akn.element.Attributes.biConsumerDateTime;
import static io.legaldocml.akn.element.Attributes.biConsumerRoleRef;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.util.XmlWriterHelper.writeActor;
import static io.legaldocml.akn.util.XmlWriterHelper.writeAgent;
import static io.legaldocml.akn.util.XmlWriterHelper.writeDate;
import static io.legaldocml.akn.util.XmlWriterHelper.writeOutcome;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRole;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element step is a metadata element specifying facts about a workflow step occurred to the document. For each
 * event, a date, a type, an agent (and the corresponding role) that generated the action must be referenced. The
 * outcome, too, can be specified.
 *
 * <pre>
 *   <xsd:element name="step">
 * 	   <xsd:complexType>
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="anyOtherType">
 * 		     <xsd:attributeGroup ref="date"/>
 * 			 <xsd:attributeGroup ref="agent"/>
 * 			 <xsd:attributeGroup ref="actor"/>
 * 		 	 <xsd:attributeGroup ref="role"/>
 * 		 	 <xsd:attributeGroup ref="outcome"/>
 * 		   <xsd:extension>
 * 	     <xsd:complexContent>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Step extends AnyOtherType implements Role, Date, Outcome, Actor, Agent {

    /**
     * Memory address.
     */
    private static final long ADDRESS_STEP = Buffers.address(STEP);

    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(AnyOtherType.ATTRIBUTES)
                .put(AknAttributes.DATE, biConsumerDateTime(getFieldOffset(Step.class, "date")))
                .put(AknAttributes.AS, biConsumerRoleRef(getFieldOffset(Step.class, "as")))
                .put(AknAttributes.OUTCOME, biConsumerConceptRef(getFieldOffset(Step.class, "outcome")))
                .put(AknAttributes.ACTOR, biConsumerString(getFieldOffset(Step.class, "actor")))
                .put(AknAttributes.BY, biConsumerAgentRef(getFieldOffset(Step.class, "by")))
                .build();
    }

    private RoleRef as;
    private OffsetDateTime date;
    private ConceptRef outcome;
    private String actor;
    private AgentRef by;

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
    public RoleRef getAs() {
        return this.as;
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
    public ConceptRef getOutcome() {
        return this.outcome;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOutcome(ConceptRef outcome) {
        this.outcome = outcome;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getActor() {
        return this.actor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActor(String actor) {
        this.actor = actor;
    }

    @Override
    public AgentRef getBy() {
        return this.by;
    }

    @Override
    public void setBy(AgentRef by) {
        this.by = by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_STEP, 4);
        writeDate(writer, this);
        writeOutcome(writer, this);
        writeActor(writer, this);
        writeRole(writer, this);
        if (writer.getVersion() > 2) {
            writeAgent(writer, this);
        }
        super.write(writer);
        writer.writeEnd(ADDRESS_STEP, 4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return STEP;
    }

    @Override
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }
}
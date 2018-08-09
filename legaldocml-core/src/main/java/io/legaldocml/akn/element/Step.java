package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Agent;
import io.legaldocml.akn.attribute.Date;
import io.legaldocml.akn.attribute.Outcome;
import io.legaldocml.akn.attribute.RefersOpt;
import io.legaldocml.akn.attribute.Role;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.ConceptRef;
import io.legaldocml.akn.type.ListReferenceRef;
import io.legaldocml.akn.type.RoleRef;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;
import java.time.OffsetDateTime;

import static io.legaldocml.akn.AknAttributes.AS;
import static io.legaldocml.akn.AknAttributes.BY;
import static io.legaldocml.akn.AknAttributes.DATE;
import static io.legaldocml.akn.AknAttributes.OUTCOME;
import static io.legaldocml.akn.AknAttributes.REFERS_TO;
import static io.legaldocml.akn.AknElements.STEP;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4AgentRef;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4ConceptRef;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4DateTime;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4RoleRef;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.akn.util.XmlWriterHelper.writeAgent;
import static io.legaldocml.akn.util.XmlWriterHelper.writeDate;
import static io.legaldocml.akn.util.XmlWriterHelper.writeOutcome;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRefers;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRole;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element step is a metadata element specifying facts about a workflow step occurred to the document. For each
 * event, a date, a type, an agent (and the corresponding role) that generated the action must be referenced. The
 * outcome, too, can be specified.
 * <p>
 * <pre>
 *   <xsd:element name="step">
 * 	   <xsd:complexType>
 *          <xsd:complexContent>
 *             <xsd:extension base="anyOtherType">
 *                <xsd:attributeGroup ref="date"/>
 *                <xsd:attributeGroup ref="agent"/>
 *                <xsd:attributeGroup ref="role"/>
 *                <xsd:attributeGroup ref="refers"/>
 *                <xsd:attributeGroup ref="outcome"/>
 *             </xsd:extension>
 *          </xsd:complexContent>
 *       </xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 * @author <a href="mailto:mustapha.charboub@gmail.com">Mustapha CHARBOUB</a>
 */
public final class Step extends AnyOtherType implements Role, Date, RefersOpt, Outcome, Agent {

    /**
     * Memory address.
     */
    private static final long ADDRESS_STEP = Buffers.address(STEP);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(AnyOtherType.ATTRIBUTES)
                .put(DATE, attributeGetterSetter4DateTime(DATE, getFieldOffset(Step.class, "date")))
                .put(AS, attributeGetterSetter4RoleRef(AS, getFieldOffset(Step.class, "as")))
                .put(OUTCOME, attributeGetterSetter4ConceptRef(OUTCOME, getFieldOffset(Step.class, "outcome")))
                .put(REFERS_TO, attributeGetterSetter4String(REFERS_TO, getFieldOffset(Step.class, "refersTo")))
                .put(BY, attributeGetterSetter4AgentRef(BY, getFieldOffset(Step.class, "by")))
                .build();
    }

    private RoleRef as;
    private OffsetDateTime date;
    private ConceptRef outcome;
    private ListReferenceRef refersTo;
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
    public ListReferenceRef getRefersTo() {
        return refersTo;
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
        writeRefers(writer, this);
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
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }
}
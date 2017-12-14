package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Core;
import io.legaldocml.akn.attribute.Enactment;
import io.legaldocml.akn.attribute.IdReq;
import io.legaldocml.akn.attribute.Modifiers;
import io.legaldocml.akn.attribute.RefersOpt;
import io.legaldocml.akn.type.ListReferenceRef;
import io.legaldocml.akn.type.StatusType;
import io.legaldocml.akn.type.TemporalGroupRef;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.io.Attribute;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.ArrayList;

import static io.legaldocml.akn.AknAttributes.EXCLUSION;
import static io.legaldocml.akn.AknAttributes.INCOMPLETE;
import static io.legaldocml.akn.AknAttributes.PERIOD;
import static io.legaldocml.akn.AknAttributes.REFERS_TO;
import static io.legaldocml.akn.AknAttributes.SOURCE;
import static io.legaldocml.akn.AknAttributes.STATUS;
import static io.legaldocml.akn.AknElements.CONDITION;
import static io.legaldocml.akn.AknElements.DESTINATION;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4Boolean;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4Enum;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4ListReferenceRef;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4TemporalGroupRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writeEnactment;
import static io.legaldocml.akn.util.XmlWriterHelper.writeModifiers;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRefers;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The complex type judicialArgumentType lists all the properties associated to judicial elements.
 * <p>
 * <p>
 * <pre>
 *   <xsd:complexType name="judicialArgumentType">
 * 	   <xsd:sequence>
 *       <xsd:element ref="source" minOccurs="1" maxOccurs="unbounded"/>
 *       <xsd:element ref="destination" minOccurs="1" maxOccurs="unbounded"/>
 *       <xsd:element ref="condition" minOccurs="0" maxOccurs="1"/>
 *     <xsd:sequence>
 *     <xsd:attributeGroup ref="core"/>
 *     <xsd:attributeGroup ref="idreq"/>
 *     <xsd:attributeGroup ref="enactment"/>
 *     <xsd:attributeGroup ref="modifiers"/>
 *     <xsd:attributeGroup ref="refers"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class JudicialArgumentType extends AbstractId implements IdReq, Enactment, RefersOpt, Modifiers, Core {

    protected static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(AbstractId.ATTRIBUTES)
                .put(STATUS, attributeGetterSetter4Enum(STATUS, getFieldOffset(JudicialArgumentType.class, "statusType"), StatusType.class))
                .put(PERIOD, attributeGetterSetter4TemporalGroupRef(PERIOD, getFieldOffset(JudicialArgumentType.class, "period")))
                .put(REFERS_TO, attributeGetterSetter4ListReferenceRef(REFERS_TO, getFieldOffset(JudicialArgumentType.class, "refersTo")))
                .put(EXCLUSION, attributeGetterSetter4Boolean(EXCLUSION, getFieldOffset(JudicialArgumentType.class, "exclusion")))
                .put(INCOMPLETE, attributeGetterSetter4Boolean(INCOMPLETE, getFieldOffset(JudicialArgumentType.class, "incomplete")))
                .build();
    }

    // enactment
    private TemporalGroupRef period;
    private StatusType statusType;
    // RefersOpt
    private ListReferenceRef refersTo;
    // Modifiers
    private Boolean exclusion;
    private Boolean incomplete;
    // Core
    private java.util.List<Attribute> attributes;

    private final AknList<Source> sources = new AknList<>(new Source[2]);
    private final AknList<Destination> destinations = new AknList<>(new Destination[2]);
    private Condition condition;

    /**
     * {@inheritDoc}
     */
    @Override
    public TemporalGroupRef getPeriod() {
        return this.period;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPeriod(TemporalGroupRef period) {
        this.period = period;
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
    public StatusType getStatus() {
        return this.statusType;
    }

    /**
     * {@inheritDoc}
     */
    public void setStatus(StatusType status) {
        this.statusType = status;
    }

    /**
     * {@inheritDoc}
     */
    public Boolean getExclusion() {
        return this.exclusion;
    }

    /**
     * {@inheritDoc}
     */
    public void setExclusion(Boolean exclusion) {
        this.exclusion = exclusion;
    }

    /**
     * {@inheritDoc}
     */
    public Boolean getIncomplete() {
        return this.incomplete;
    }

    /**
     * {@inheritDoc}
     */
    public void setIncomplete(Boolean incomplete) {
        this.incomplete = incomplete;
    }

    public void addSource(Source source) {
        this.sources.add(source);
    }

    public void addDestination(Destination destination) {
        this.destinations.add(destination);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Attribute attribute) {
        if (this.attributes == null) {
            this.attributes = new ArrayList<>();
        }
        this.attributes.add(attribute);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        IdReq.super.write(writer);
        writeRefers(writer, this);
        writeEnactment(writer, this);
        writeModifiers(writer, this);
        if (this.attributes != null) {
            for (int i = 0, n = this.attributes.size(); i < n; i++) {
                this.attributes.get(i).write(writer);
            }
        }
        this.sources.write(writer);
        this.destinations.write(writer);
        if (this.condition != null) {
            this.condition.write(writer);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);

        reader.nextStartOrEndElement();

        if (reader.getQName().equalsLocalName(SOURCE)) {
            Source source;
            do {
                source = new Source();
                source.read(reader);
                this.sources.add(source);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(SOURCE));
        }

        if (reader.getQName().equalsLocalName(DESTINATION)) {
            Destination destination;
            do {
                destination = new Destination();
                destination.read(reader);
                this.destinations.add(destination);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(DESTINATION));
        }

        if (reader.getQName().equalsLocalName(CONDITION)) {
            this.condition = new Condition();
            this.condition.read(reader);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }

}
package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Source;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;
import io.legaldocml.util.ListIterable;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.SOURCE;
import static io.legaldocml.akn.AknElements.ACTIVE_MODIFICATIONS;
import static io.legaldocml.akn.AknElements.ANALYSIS;
import static io.legaldocml.akn.AknElements.JUDICIAL;
import static io.legaldocml.akn.AknElements.MAPPINGS;
import static io.legaldocml.akn.AknElements.OTHER_ANALYSIS;
import static io.legaldocml.akn.AknElements.OTHER_REFERENCES;
import static io.legaldocml.akn.AknElements.PARLIAMENTARY;
import static io.legaldocml.akn.AknElements.PASSIVE_MODIFICATIONS;
import static io.legaldocml.akn.AknElements.RESTRICTIONS;
import static io.legaldocml.akn.element.Attributes.ATTRIBUTE_CONSUMER;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4AgentRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writeSource;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;
import static io.legaldocml.util.Iterables.iterable;

/**
 * <pre>
 *   <xsd:element name="analysis">
 *     <xsd:complexType>
 *       <xsd:sequence>
 *         <xsd:element ref="activeModifications" minOccurs="0" maxOccurs="1"/>
 *         <xsd:element ref="passiveModifications" minOccurs="0" maxOccurs="1"/>
 *         <xsd:element ref="restrictions" minOccurs="0" maxOccurs="1"/>
 *         <xsd:element ref="judicial" minOccurs="0" maxOccurs="1"/>
 *         <xsd:element ref="parliamentary" minOccurs="0" maxOccurs="1"/>
 *         <xsd:element ref="mappings" minOccurs="0" maxOccurs="1"/>
 *         <xsd:element ref="otherReferences" minOccurs="0" maxOccurs="unbounded"/>
 *         <xsd:element ref="otherAnalysis" minOccurs="0" maxOccurs="unbounded"/>
 *        <xsd:sequence>
 *        <xsd:attributeGroup ref="source" />
 *     <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Analysis implements Source {

    /**
     * Memory address.
     */
    private static final long ADDRESS_ANALYSIS = Buffers.address(ANALYSIS);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .put(SOURCE, attributeGetterSetter4AgentRef(SOURCE, getFieldOffset(Analysis.class, "source")))
                .build();
    }

    private AgentRef source;

    private ActiveModifications activeModifications;
    private PassiveModifications passiveModifications;
    private Restrictions restrictions;
    private Judicial judicial;
    private Parliamentary parliamentary;
    private Mappings mappings;
    private AknList<OtherReferences> otherReferences;
    private AknList<OtherAnalysis> otherAnalysis;

    private AknObject parent;

    /**
     * {@inheritDoc}
     */
    public AgentRef getSource() {
        return this.source;
    }

    /**
     * {@inheritDoc}
     */
    public void setSource(AgentRef source) {
        this.source = source;
    }

    public ActiveModifications getActiveModifications() {
        return this.activeModifications;
    }

    public void setActiveModifications(ActiveModifications activeModifications) {
        this.activeModifications = activeModifications;
    }

    public PassiveModifications getPassiveModifications() {
        return passiveModifications;
    }

    public void setPassiveModifications(PassiveModifications passiveModifications) {
        this.passiveModifications = passiveModifications;
    }

    public Restrictions getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(Restrictions restrictions) {
        this.restrictions = restrictions;
    }

    public Judicial getJudicial() {
        return judicial;
    }

    public void setJudicial(Judicial judicial) {
        this.judicial = judicial;
    }

    public Parliamentary getParliamentary() {
        return parliamentary;
    }

    public void setParliamentary(Parliamentary parliamentary) {
        this.parliamentary = parliamentary;
    }

    public Mappings getMappings() {
        return mappings;
    }

    public void setMappings(Mappings mappings) {
        this.mappings = mappings;
    }

    public ListIterable<OtherReferences> getOtherReferences() {
        return iterable(this.otherReferences);
    }

    public ListIterable<OtherAnalysis> getOtherAnalysis() {
        return iterable(this.otherAnalysis);
    }

    public void add(OtherAnalysis otherAnalysis) {
        if (this.otherAnalysis == null) {
            this.otherAnalysis = new AknList<>(new OtherAnalysis[2]);
        }
        this.otherAnalysis.add(otherAnalysis);
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
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_ANALYSIS, 8);
        writeSource(writer, this);

        if (this.activeModifications != null) {
            this.activeModifications.write(writer);
        }

        if (this.passiveModifications != null) {
            this.passiveModifications.write(writer);
        }

        if (this.restrictions != null) {
            this.restrictions.write(writer);
        }

        if (this.judicial != null) {
            this.judicial.write(writer);
        }

        if (this.parliamentary != null) {
            this.parliamentary.write(writer);
        }

        if (this.mappings != null) {
            this.mappings.write(writer);
        }

        if (this.otherReferences != null && this.otherReferences.size() > 0) {
            this.otherReferences.write(writer);
        }

        if (this.otherAnalysis != null && this.otherAnalysis.size() > 0) {
            this.otherAnalysis.write(writer);
        }

        writer.writeEnd(ADDRESS_ANALYSIS, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        reader.forEach(this, ATTRIBUTE_CONSUMER);
        reader.nextStartOrEndElement();

        if (reader.getQName().equalsLocalName(ACTIVE_MODIFICATIONS)) {
            this.activeModifications = new ActiveModifications();
            this.activeModifications.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(PASSIVE_MODIFICATIONS)) {
            this.passiveModifications = new PassiveModifications();
            this.passiveModifications.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(RESTRICTIONS)) {
            this.restrictions = new Restrictions();
            this.restrictions.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(JUDICIAL)) {
            this.judicial = new Judicial();
            this.judicial.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(PARLIAMENTARY)) {
            this.parliamentary = new Parliamentary();
            this.parliamentary.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(MAPPINGS)) {
            this.mappings = new Mappings();
            this.mappings.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(OTHER_REFERENCES)) {
            OtherReferences otherReferences;
            this.otherReferences = new AknList<>(new OtherReferences[4]);
            do {
                otherReferences = new OtherReferences();
                otherReferences.read(reader);
                this.otherReferences.add(otherReferences);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(OTHER_REFERENCES));
        }

        if (reader.getQName().equalsLocalName(OTHER_ANALYSIS)) {
            OtherAnalysis otherAnalysis;
            this.otherAnalysis = new AknList<>(new OtherAnalysis[4]);
            do {
                otherAnalysis = new OtherAnalysis();
                otherAnalysis.read(reader);
                this.otherAnalysis.add(otherAnalysis);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(OTHER_ANALYSIS));
        }

    }

    /**
     * {@inheritDoc}
     */
    public String name() {
        return ANALYSIS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {

            visitor.visitLeave(this);
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends AknObject> T getParent() {
        return (T)parent;
    }

    public <T extends AknObject> void setParent(T parent) {
        this.parent = parent;
    }

}
package io.legaldocml.akn.element;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import javax.xml.stream.XMLStreamConstants;
import java.io.IOException;
import java.util.stream.Stream;

import static io.legaldocml.akn.AknElements.ANALYSIS;
import static io.legaldocml.akn.AknElements.CLASSIFICATION;
import static io.legaldocml.akn.AknElements.LIFECYCLE;
import static io.legaldocml.akn.AknElements.META;
import static io.legaldocml.akn.AknElements.NOTES;
import static io.legaldocml.akn.AknElements.PRESENTATION;
import static io.legaldocml.akn.AknElements.PROPRIETARY;
import static io.legaldocml.akn.AknElements.PUBLICATION;
import static io.legaldocml.akn.AknElements.REFERENCES;
import static io.legaldocml.util.Streams.stream;

/**
 * <pre>
 *   <xsd:element name="meta">
 *     <xsd:complexType>
 *       <xsd:sequence>
 *         <xsd:element ref="identification"/>
 *         <xsd:element ref="publication" minOccurs="0" maxOccurs="1"/>
 *         <xsd:element ref="classification" minOccurs="0" maxOccurs="unbounded"/>
 *         <xsd:element ref="lifecycle" minOccurs="0" maxOccurs="unbounded"/>
 *         <xsd:element ref="workflow" minOccurs="0" maxOccurs="unbounded"/>
 *         <xsd:element ref="analysis" minOccurs="0" maxOccurs="unbounded"/>
 *         <xsd:element ref="temporalData" minOccurs="0" maxOccurs="unbounded"/>
 *         <xsd:element ref="references" minOccurs="0" maxOccurs="unbounded"/>
 *         <xsd:element ref="notes" minOccurs="0" maxOccurs="unbounded"/>
 *         <xsd:element ref="proprietary" minOccurs="0" maxOccurs="unbounded"/>
 *         <xsd:element ref="presentation" minOccurs="0" maxOccurs="unbounded"/>
 *       <xsd:sequence>
 *     <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Meta implements AknObject {

    private static final long ADDRESS_META = Buffers.address(META);

    // Mandatory
    private final Identification identification = new Identification();

    // Optional
    private Publication publication;

    // Optional
    private AknList<Classification> classifications;

    // Optional
    private AknList<Lifecycle> lifecycles;

    // Optional
    private AknList<Workflow> workflows;

    // Optional
    private AknList<Analysis> analysis;

    // Optional
    private AknList<TemporalData> temporalData;

    // Optional
    private AknList<References> references;

    // Optional
    private AknList<Notes> notes;

    // Optional
    private AknList<Proprietary> proprietaries;

    // Optional
    private AknList<Presentation> presentations;

    public Identification getIdentification() {
        return this.identification;
    }

    public Publication getPublication() {
        return publication;
    }

    public Stream<Classification> getClassifications() {
        return stream(this.classifications);
    }

    public Stream<Lifecycle> getLifecycles() {
        return stream(this.lifecycles);
    }

    public Stream<Workflow> getWorkflows() {
        return stream(this.workflows);
    }

    public Stream<Analysis> getAnalysis() {
        return stream(this.analysis);
    }

    public Stream<TemporalData> getTemporalData() {
        return stream(this.temporalData);
    }

    public Stream<Notes> getNotes() {
        return stream(this.notes);
    }

    public Stream<Proprietary> getProprietaries() {
        return stream(this.proprietaries);
    }

    public Stream<Presentation> getPresentations() {
        return stream(this.presentations);
    }

    public Stream<References> getReferences() {
        return stream(this.references);
    }

    public References getReferences(AgentRef source) {
        if (this.references == null) {
            return null;
        }
        for (References ref : this.references) {
            if (source.equals(ref.getSource())) {
                return ref;
            }
        }
        return null;
    }

    public void add(References references) {
        if (this.references == null) {
            this.references = new AknList<>(new References[2]);
        }
        this.references.add(references);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return META;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {

        writer.writeStart(ADDRESS_META, 4);
        this.identification.write(writer);

        if (this.publication != null) {
            this.publication.write(writer);
        }

        if (this.classifications != null) {
            this.classifications.write(writer);
        }

        if (this.lifecycles != null) {
            this.lifecycles.write(writer);
        }

        if (this.workflows != null) {
            this.workflows.write(writer);
        }

        if (this.analysis != null && this.analysis.size() > 0) {
            this.analysis.write(writer);
        }

        if (this.temporalData != null && this.temporalData.size() > 0) {
            this.temporalData.write(writer);
        }

        if (this.references != null && this.references.size() > 0) {
            this.references.write(writer);
        }

        if (this.notes != null && this.notes.size() > 0) {
            this.notes.write(writer);
        }

        if (this.proprietaries != null && this.proprietaries.size() > 0) {
            this.proprietaries.write(writer);
        }

        writer.writeEnd(ADDRESS_META, 4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {

        reader.nextStartOrEndElement();
        this.identification.read(reader);

        if (reader.getQName().equalsLocalName(PUBLICATION)) {
            this.publication = new Publication();
            this.publication.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(CLASSIFICATION)) {
            Classification classification;
            this.classifications = new AknList<>(new Classification[4]);
            do {
                classification = new Classification();
                classification.read(reader);
                this.classifications.add(classification);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(CLASSIFICATION));
        }

        if (reader.getQName().equalsLocalName(LIFECYCLE)) {
            Lifecycle lifecycle;
            this.lifecycles = new AknList<>(new Lifecycle[4]);
            do {
                lifecycle = new Lifecycle();
                lifecycle.read(reader);
                this.lifecycles.add(lifecycle);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(LIFECYCLE));
        }

        if (reader.getQName().equalsLocalName(Workflow.ELEMENT)) {
            Workflow workflow;
            this.workflows = new AknList<>(new Workflow[4]);
            do {
                workflow = new Workflow();
                workflow.read(reader);
                this.workflows.add(workflow);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(Workflow.ELEMENT));
        }

        if (reader.getQName().equalsLocalName(ANALYSIS)) {
            Analysis analysis;
            this.analysis = new AknList<>(new Analysis[4]);
            do {
                analysis = new Analysis();
                analysis.read(reader);
                this.analysis.add(analysis);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(ANALYSIS));
        }

        if (reader.getQName().equalsLocalName(TemporalData.ELEMENT)) {
            TemporalData temporalData;
            this.temporalData = new AknList<>(new TemporalData[4]);
            do {
                temporalData = new TemporalData();
                temporalData.read(reader);
                this.temporalData.add(temporalData);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(TemporalData.ELEMENT));
        }

        if (reader.getQName().equalsLocalName(REFERENCES)) {
            References ref;
            this.references = new AknList<>(new References[2]);
            do {
                ref = new References();
                ref.read(reader);
                this.references.add(ref);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(REFERENCES));
        }

        if (reader.getQName().equalsLocalName(NOTES)) {
            Notes notes;
            this.notes = new AknList<>(new Notes[4]);
            do {
                notes = new Notes();
                notes.read(reader);
                this.notes.add(notes);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(NOTES));
        }

        if (reader.getQName().equalsLocalName(PROPRIETARY)) {
            Proprietary proprietary;
            this.proprietaries = new AknList<>(new Proprietary[4]);
            do {
                proprietary = new Proprietary();
                proprietary.read(reader);
                this.proprietaries.add(proprietary);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(PROPRIETARY));
        }

        if (reader.getQName().equalsLocalName(PRESENTATION)) {
            Presentation presentation;
            this.presentations = new AknList<>(new Presentation[4]);
            do {
                presentation = new Presentation();
                presentation.read(reader);
                this.presentations.add(presentation);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(PRESENTATION));
        }

        if (reader.getEventType() == XMLStreamConstants.END_ELEMENT && reader.getQName().equalsLocalName(META)) {
            reader.nextStartOrEndElement();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {

        this.identification.accept(visitor);

        if (this.publication != null) {
            this.publication.accept(visitor);
        }

        if (this.classifications != null) {
            this.classifications.accept(visitor);
        }

        if (this.lifecycles != null) {
            this.lifecycles.accept(visitor);
        }

        if (this.workflows != null) {
            this.workflows.accept(visitor);
        }

        if (this.analysis != null) {
            this.analysis.accept(visitor);
        }

        if (this.temporalData != null) {
            this.temporalData.accept(visitor);
        }

        if (this.references != null) {
            this.references.accept(visitor);
        }

        if (this.notes != null) {
            this.notes.accept(visitor);
        }

        if (this.proprietaries != null) {
            this.proprietaries.accept(visitor);
        }

        if (this.presentations != null) {
            this.presentations.accept(visitor);
        }

    }
}
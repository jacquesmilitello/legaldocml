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

/**
 * <pre>
 *   &lt;xsd:element name="meta"&gt;
 *     &lt;xsd:complexType&gt;
 *       &lt;xsd:sequence&gt;
 *         &lt;xsd:element ref="identification"/&gt;
 *         &lt;xsd:element ref="publication" minOccurs="0" maxOccurs="1"/&gt;
 *         &lt;xsd:element ref="classification" minOccurs="0" maxOccurs="unbounded"/&gt;
 *         &lt;xsd:element ref="lifecycle" minOccurs="0" maxOccurs="unbounded"/&gt;
 *         &lt;xsd:element ref="workflow" minOccurs="0" maxOccurs="unbounded"/&gt;
 *         &lt;xsd:element ref="analysis" minOccurs="0" maxOccurs="unbounded"/&gt;
 *         &lt;xsd:element ref="temporalData" minOccurs="0" maxOccurs="unbounded"/&gt;
 *         &lt;xsd:element ref="references" minOccurs="0" maxOccurs="unbounded"/&gt;
 *         &lt;xsd:element ref="notes" minOccurs="0" maxOccurs="unbounded"/&gt;
 *         &lt;xsd:element ref="proprietary" minOccurs="0" maxOccurs="unbounded"/&gt;
 *         &lt;xsd:element ref="presentation" minOccurs="0" maxOccurs="unbounded"/&gt;
 *       &lt;xsd:sequence&gt;
 *     &lt;xsd:complexType&gt;
 *   &lt;xsd:element&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Meta implements AknObject {

    /**
     * Xml element name.
     */
    public static final String ELEMENT = "meta";

    private static final long ADDRESS = Buffers.address(ELEMENT);

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

    public AknList<References> getReferences() {
        return this.references;
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
        return ELEMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {

        writer.writeStart(ADDRESS, 4);
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

        writer.writeEnd(ADDRESS, 4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {

        reader.nextStartOrEndElement();
        this.identification.read(reader);

        if (reader.getQName().equalsLocalName(Publication.ELEMENT)) {
            this.publication = new Publication();
            this.publication.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(Classification.ELEMENT)) {
            Classification classification;
            this.classifications = new AknList<>(new Classification[4]);
            do {
                classification = new Classification();
                classification.read(reader);
                this.classifications.add(classification);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(Classification.ELEMENT));
        }

        if (reader.getQName().equalsLocalName(Lifecycle.ELEMENT)) {
            Lifecycle lifecycle;
            this.lifecycles = new AknList<>(new Lifecycle[4]);
            do {
                lifecycle = new Lifecycle();
                lifecycle.read(reader);
                this.lifecycles.add(lifecycle);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(Lifecycle.ELEMENT));
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

        if (reader.getQName().equalsLocalName(Analysis.ELEMENT)) {
            Analysis analysis;
            this.analysis = new AknList<>(new Analysis[4]);
            do {
                analysis = new Analysis();
                analysis.read(reader);
                this.analysis.add(analysis);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(Analysis.ELEMENT));
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

        if (reader.getQName().equalsLocalName(References.ELEMENT)) {
            References ref;
            this.references = new AknList<>(new References[2]);
            do {
                ref = new References();
                ref.read(reader);
                this.references.add(ref);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(References.ELEMENT));
        }

        if (reader.getQName().equalsLocalName(Notes.ELEMENT)) {
            Notes notes;
            this.notes = new AknList<>(new Notes[4]);
            do {
                notes = new Notes();
                notes.read(reader);
                this.notes.add(notes);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(Notes.ELEMENT));
        }

        if (reader.getQName().equalsLocalName(Proprietary.ELEMENT)) {
            Proprietary proprietary;
            this.proprietaries = new AknList<>(new Proprietary[4]);
            do {
                proprietary = new Proprietary();
                proprietary.read(reader);
                this.proprietaries.add(proprietary);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(Proprietary.ELEMENT));
        }

        if (reader.getQName().equalsLocalName(Presentation.ELEMENT)) {
            Presentation presentation;
            this.presentations = new AknList<>(new Presentation[4]);
            do {
                presentation = new Presentation();
                presentation.read(reader);
                this.presentations.add(presentation);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(Presentation.ELEMENT));
        }

        if (reader.getEventType() == XMLStreamConstants.END_ELEMENT && reader.getQName().equalsLocalName(ELEMENT)) {
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
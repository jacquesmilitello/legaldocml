package io.legaldocml.akn.element;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.akn.HasCoverPage;
import io.legaldocml.akn.attribute.Core;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import javax.xml.stream.XMLStreamConstants;
import java.io.IOException;

import static io.legaldocml.akn.AknElements.ATTACHMENTS;
import static io.legaldocml.akn.AknElements.COMPONENTS;
import static io.legaldocml.akn.AknElements.CONCLUSIONS;
import static io.legaldocml.akn.AknElements.COVER_PAGE;
import static io.legaldocml.akn.AknElements.PREFACE;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
abstract class AbstractStructure implements AknObject, Core, HasCoverPage {

    // Mandatory
    private final Meta meta = new Meta();

    // Optional
    private CoverPage coverPage;

    // Optional
    private Preface preface;

    // Optional
    private Attachments attachments;

    // Optional
    private Conclusions conclusions;

    // Optional
    private Components components;

    public final Meta getMeta() {
        return meta;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final CoverPage getCoverPage() {
        return this.coverPage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setCoverPage(CoverPage coverPage) {
        this.coverPage = coverPage;
    }

    public final Preface getPreface() {
        return preface;
    }

    public final void setPreface(Preface preface) {
        this.preface = preface;
    }

    public final Attachments getAttachments() {
        return attachments;
    }

    public final void setAttachments(Attachments attachments) {
        this.attachments = attachments;
    }

    public final Conclusions getConclusions() {
        return conclusions;
    }

    public final void setConclusions(Conclusions conclusions) {
        this.conclusions = conclusions;
    }

    public final Components getComponents() {
        return components;
    }

    public final void setComponents(Components components) {
        this.components = components;
    }

    protected final void writeMeta(XmlWriter writer) throws IOException {
        this.meta.write(writer);
    }

    protected final void writeCoverPage(XmlWriter writer) throws IOException {
        if (this.coverPage != null) {
            this.coverPage.write(writer);
        }
    }

    protected final void writeMetaCoverPagePreface(XmlWriter writer) throws IOException {
        this.meta.write(writer);

        if (this.coverPage != null) {
            this.coverPage.write(writer);
        }

        if (this.preface != null) {
            this.preface.write(writer);
        }
    }


    protected final void writeConclusionsAttachments(XmlWriter writer) throws IOException {
        if (this.conclusions != null) {
            this.conclusions.write(writer);
        }

        if (this.attachments != null) {
            this.attachments.write(writer);
        }

        if (this.components != null) {
            // only since writer >= V3
            this.components.write(writer);
        }
    }

    protected final void readMeta(XmlReader reader) {
        this.meta.read(reader);
    }

    protected final void readCoverPage(XmlReader reader) {
        if (reader.getQName().equalsLocalName(COVER_PAGE)) {
            this.coverPage = new CoverPage();
            this.coverPage.read(reader);
            reader.nextStartOrEndElement();
        }
    }

    protected final void readMetaCoverPagePreface(XmlReader reader) {

        this.meta.read(reader);

        if (reader.getQName().equalsLocalName(COVER_PAGE)) {
            this.coverPage = new CoverPage();
            this.coverPage.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(PREFACE)) {
            this.preface = new Preface();
            this.preface.read(reader);
            reader.nextStartOrEndElement();
        }

    }

    protected final void readConclusionsAttachments(XmlReader reader) {

        if (reader.getEventType() != XMLStreamConstants.END_DOCUMENT && reader.getQName().equalsLocalName(CONCLUSIONS)) {
            this.conclusions = new Conclusions();
            this.conclusions.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getEventType() != XMLStreamConstants.END_DOCUMENT && reader.getQName().equalsLocalName(ATTACHMENTS)) {
            if (reader.<AkomaNtosoContext>getContext().getAkoXmlModule().getVersion() == 2) {
                this.attachments = new AttachmentsV2();
            } else {
                this.attachments = new AttachmentsV3();
            }
            this.attachments.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getEventType() != XMLStreamConstants.END_DOCUMENT && reader.getQName().equalsLocalName(COMPONENTS)) {
            this.components = new Components();
            this.components.read(reader);
            reader.nextStartOrEndElement();
        }
    }

    protected final void visitMeta(AknVisitor visitor) {
        Meta m = this.meta;
        if (visitor.visitEnter(m)) {
            m.accept(visitor);
            visitor.visitLeave(m);
        }
    }

    protected final void visitCoverPage(AknVisitor visitor) {
        CoverPage cp = this.coverPage;
        if (cp != null && visitor.visitEnter(cp)) {
            cp.accept(visitor);
            visitor.visitLeave(cp);
        }
    }

    protected final void visitPreface(AknVisitor visitor) {
        Preface p = this.preface;
        if (p != null && visitor.visitEnter(p)) {
            p.accept(visitor);
            visitor.visitLeave(p);
        }
    }

    protected final void visitConclusions(AknVisitor visitor) {
        Conclusions c = this.conclusions;
        if (c != null && visitor.visitEnter(c)) {
            c.accept(visitor);
            visitor.visitLeave(c);
        }
    }

    protected final void visitAttachments(AknVisitor visitor) {
        Attachments a = this.attachments;
        if (a != null && visitor.visitEnter(a)) {
            a.accept(visitor);
            visitor.visitLeave(a);
        }
    }

    protected final void visitComponents(AknVisitor visitor) {
        Components c = this.components;
        if (c != null && visitor.visitEnter(c)) {
            c.accept(visitor);
            visitor.visitLeave(c);
        }
    }


}

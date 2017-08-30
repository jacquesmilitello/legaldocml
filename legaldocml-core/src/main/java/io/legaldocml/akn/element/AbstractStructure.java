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
        if (reader.getQName().equalsLocalName(CoverPage.ELEMENT)) {
            this.coverPage = new CoverPage();
            this.coverPage.read(reader);
            reader.nextStartOrEndElement();
        }
    }

    protected final void readMetaCoverPagePreface(XmlReader reader) {

        this.meta.read(reader);

        if (reader.getQName().equalsLocalName(CoverPage.ELEMENT)) {
            this.coverPage = new CoverPage();
            this.coverPage.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(Preface.ELEMENT)) {
            this.preface = new Preface();
            this.preface.read(reader);
            reader.nextStartOrEndElement();
        }

    }

    protected final void readConclusionsAttachments(XmlReader reader) {

        if (reader.getEventType() != XMLStreamConstants.END_DOCUMENT && reader.getQName().equalsLocalName(Conclusions.ELEMENT)) {
            this.conclusions = new Conclusions();
            this.conclusions.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getEventType() != XMLStreamConstants.END_DOCUMENT && reader.getQName().equalsLocalName(Attachments.ELEMENT)) {
            if (reader.<AkomaNtosoContext>getContext().getAkoXmlModule().getVersion() == 2) {
                this.attachments = new AttachmentsV2();
            } else {
                this.attachments = new AttachmentsV3();
            }
            this.attachments.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getEventType() != XMLStreamConstants.END_DOCUMENT && reader.getQName().equalsLocalName(Components.ELEMENT)) {
            this.components = new Components();
            this.components.read(reader);
            reader.nextStartOrEndElement();
        }
    }

    protected final void visitMeta(AknVisitor visitor) {
        Meta meta = this.meta;
        if (visitor.visitEnter(meta)) {
            meta.accept(visitor);
            visitor.visitLeave(meta);
        }
    }

    protected final void visitCoverPage(AknVisitor visitor) {
        CoverPage coverPage = this.coverPage;
        if (coverPage != null && visitor.visitEnter(coverPage)) {
            coverPage.accept(visitor);
            visitor.visitLeave(coverPage);
        }
    }

    protected final void visitPreface(AknVisitor visitor) {
        Preface preface = this.preface;
        if (preface != null && visitor.visitEnter(preface)) {
            preface.accept(visitor);
            visitor.visitLeave(preface);
        }
    }

    protected final void visitConclusions(AknVisitor visitor) {
        Conclusions conclusions = this.conclusions;
        if (conclusions != null && visitor.visitEnter(conclusions)) {
            conclusions.accept(visitor);
            visitor.visitLeave(conclusions);
        }
    }

    protected final void visitAttachments(AknVisitor visitor) {
        Attachments attachments = this.attachments;
        if (attachments != null && visitor.visitEnter(attachments)) {
            attachments.accept(visitor);
            visitor.visitLeave(attachments);
        }
    }

    protected final void visitComponents(AknVisitor visitor) {
        Components components = this.components;
        if (components != null && visitor.visitEnter(components)) {
            components.accept(visitor);
            visitor.visitLeave(components);
        }
    }


}

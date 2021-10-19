package io.legaldocml.akn.element;

import io.legaldocml.akn.AknElements;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.ATTACHMENTS;

/**
 *
 * <pre>
 *   <xsd:element name="attachments">
 *     <xsd:complexType>
 *       <xsd:sequence minOccurs="1" maxOccurs="unbounded">
 *         <xsd:element ref="attachment"/>
 *       <xsd:sequence>
 *     <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AttachmentsV3 implements Attachments {

    /**
     * Memory address.
     */
    private static final long ADDRESS_ATTACHMENTS = Buffers.address(ATTACHMENTS);

    // Mandatory (min 1).
    private final AknList<Attachment> elements = new AknList<>(new Attachment[4]);

    private AknObject parent;

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_ATTACHMENTS, 11);
        this.elements.write(writer);
        writer.writeEnd(ADDRESS_ATTACHMENTS, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        reader.nextStartOrEndElement();
        if (reader.getQName().equalsLocalName(AknElements.ATTACHMENT)) {
            Attachment attachment;
            do {
                attachment = new Attachment();
                attachment.read(reader);
                this.elements.add(attachment);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(AknElements.ATTACHMENT));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            this.elements.accept(visitor);
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
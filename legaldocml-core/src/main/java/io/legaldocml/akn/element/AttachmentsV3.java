package io.legaldocml.akn.element;

import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

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
    private static final long ADDRESS = Buffers.address(ELEMENT);

    // Mandatory (min 1).
    private final AknList<Attachment> elements = new AknList<>(new Attachment[4]);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 11);
        this.elements.write(writer);
        writer.writeEnd(ADDRESS, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        reader.nextStartOrEndElement();
        if (reader.getQName().equalsLocalName(Attachment.ELEMENT)) {
            Attachment attachment;
            do {
                attachment = new Attachment();
                attachment.read(reader);
                this.elements.add(attachment);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(Attachment.ELEMENT));
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
}
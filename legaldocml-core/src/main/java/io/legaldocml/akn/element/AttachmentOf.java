package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Type;
import io.legaldocml.akn.group.DocRef;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.TYPE;
import static io.legaldocml.akn.AknElements.ATTACHMENT_OF;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.akn.util.XmlWriterHelper.writeType;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element attachmentOf is a metadata reference to the Akoma Ntoso IRI of a document of which this document is an
 * attachment.
 * <p>
 * <pre>
 *   <xsd:element name="attachmentOf" type="referenceType">
 * 	   <xsd:complexType>
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="referenceType">
 * 		     <xsd:attributeGroup ref="type"/>
 * 		   <xsd:extension>
 * 		 <xsd:complexContent>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AttachmentOf extends ReferenceType implements DocRef, Type {

    /**
     * Memory address.
     */
    private static final long ADDRESS_ATTACHMENT_OF = Buffers.address(ATTACHMENT_OF);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(ReferenceType.ATTRIBUTES)
                .put(TYPE, attributeGetterSetter4String(TYPE, getFieldOffset(AttachmentOf.class, "type")))
                .build();
    }

    private String type;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setType(String type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_ATTACHMENT_OF, 12);
        writeType(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_ATTACHMENT_OF, 12);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ATTACHMENT_OF;
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
    public void accept(AknVisitor visitor) {
        visitor.visit(this);
    }

}
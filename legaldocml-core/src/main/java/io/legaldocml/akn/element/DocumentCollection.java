package io.legaldocml.akn.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.DOCUMENT_COLLECTION;

/**
 * Element documentCollection is used for describing the structure and content of a collection of other documents chosen
 * and combined for any reason whatsoever.
 *
 * <pre>
 *   <xsd:element name="documentCollection" type="collectionStructure">
 *     <xsd:unique name="eId-documentCollection">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@eId"/>
 *     <xsd:unique>
 *     <xsd:unique name="GUID-documentCollection">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@GUID"/>
 *     <xsd:unique>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DocumentCollection extends CollectionStructure implements DocumentType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DOCUMENT_COLLECTION = Buffers.address(DOCUMENT_COLLECTION);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DOCUMENT_COLLECTION, 18);
        super.write(writer);
        writer.writeEnd(ADDRESS_DOCUMENT_COLLECTION, 18);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DOCUMENT_COLLECTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            super.accept(visitor);
            visitor.visitLeave(this);
        }
    }

}
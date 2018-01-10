package io.legaldocml.akn.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.DOC;

/**
 * Element doc is used for describing the structure and content of any other document that is not included in the list
 * of document explicitly managed by Akoma Ntoso.
 *
 * <pre>
 *   <xsd:element name="doc" type="openStructure"/>
 *     <xsd:unique name="eId-doc">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@eId"/>
 *     <xsd:unique>
 *     <xsd:unique name="GUID-doc">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@GUID"/>
 *     <xsd:unique>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Doc extends OpenStructure implements DocumentType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DOC = Buffers.address(DOC);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DOC, 3);
        super.write(writer);
        writer.writeEnd(ADDRESS_DOC, 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DOC;
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
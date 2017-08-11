package io.legaldocml.akn.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * Element documentCollection is used for describing the structure and content of a collection of other documents chosen
 * and combined for any reason whatsoever.
 * <p/>
 * <pre>
 *   <xsd:element name="documentCollection" type="collectionStructure">
 *     <xsd:unique name="eId-documentCollection">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@eId"/>
 *     </xsd:unique>
 *     <xsd:unique name="GUID-documentCollection">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@GUID"/>
 *     </xsd:unique>
 *   </xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DocumentCollection extends CollectionStructure implements DocumentType {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "documentCollection";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 18);
        super.write(writer);
        writer.writeEnd(ADDRESS, 18);
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
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            super.accept(visitor);
            visitor.visitLeave(this);
        }
    }

}
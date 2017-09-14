package io.legaldocml.akn.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.JUDGMENT;

/**
 * Element judgment is used for describing the structure and content of a judgment.
 *
 * <pre>
 *   <xsd:element name="judgment" type="judgmentStructure">
 *     <xsd:unique name="eId-judgment">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@eId"/>
 *     <xsd:unique>
 *     <xsd:unique name="GUID-judgment">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@GUID"/>
 *     <xsd:unique>
 * 	 <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Judgment extends JudgmentStructure implements DocumentType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_JUDGMENT = Buffers.address(JUDGMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_JUDGMENT, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS_JUDGMENT, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return JUDGMENT;
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
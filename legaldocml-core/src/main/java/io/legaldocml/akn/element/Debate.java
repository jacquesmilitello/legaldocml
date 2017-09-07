package io.legaldocml.akn.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.DEBATE;

/**
 * <pre>
 *   <xsd:element name="debate" type="debateStructure">
 *     <xsd:unique name="eId-debate">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@eId"/>
 *     <xsd:unique>
 *     <xsd:unique name="GUID-debate">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@GUID"/>
 *     <xsd:unique>
 * 	 <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Debate extends DebateStructure implements DocumentType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DEBATE = Buffers.address(DEBATE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DEBATE, 6);
        super.write(writer);
        writer.writeEnd(ADDRESS_DEBATE, 6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DEBATE;
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
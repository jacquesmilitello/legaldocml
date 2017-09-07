package io.legaldocml.akn.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.BILL;

/**
 * Element bill is used for describing the structure and content of a bill.
 *
 * <pre>
 *   <xsd:element name="bill" type="hierarchicalStructure">
 *     <xsd:unique name="eId-bill">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@eId"/>
 *     <xsd:unique>
 *     <xsd:unique name="GUID-bill">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@GUID"/>
 *     <xsd:unique>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Bill extends HierarchicalStructure implements DocumentType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_BILL = Buffers.address(BILL);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_BILL, 4);
        super.write(writer);
        writer.writeEnd(ADDRESS_BILL, 4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return BILL;
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
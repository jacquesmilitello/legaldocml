package io.legaldocml.akn.element;


import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.FRBR_PRESCRIPTIVE;

/**
 * The element FRBRprescriptive is the metadata property containing a boolean value to determine whether the document
 * contains prescriptive text (i.e., text that is or might become prescriptive, such as an act or a bill) or not (such
 * as, for instance, a non-normative resolution from an assembly.
 *
 * <pre>
 *   <xsd:element name="FRBRprescriptive" type="booleanValueType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRprescriptive extends BooleanValueType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_FRBR_PRESCRIPTIVE = Buffers.address(FRBR_PRESCRIPTIVE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_FRBR_PRESCRIPTIVE, 16);
        super.write(writer);
        writer.writeEnd(ADDRESS_FRBR_PRESCRIPTIVE, 16);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return FRBR_PRESCRIPTIVE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        visitor.visit(this);
    }

}
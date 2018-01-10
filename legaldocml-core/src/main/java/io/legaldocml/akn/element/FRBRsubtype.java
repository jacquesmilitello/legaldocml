package io.legaldocml.akn.element;

import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.FRBR_SUBTYPE;

/**
 * The element FRBRsubtype is the metadata property containing a string for the specific subtype of the document to be
 * used in the work-level IRI of this document
 *
 * <pre>
 *  	<xsd:element name="FRBRsubtype" type="valueType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRsubtype extends ValueType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_FRBR_SUBTYPE = Buffers.address(FRBR_SUBTYPE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_FRBR_SUBTYPE, 11);
        super.write(writer);
        writer.writeEnd(ADDRESS_FRBR_SUBTYPE, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return FRBR_SUBTYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        visitor.visit(this);
    }
    
}
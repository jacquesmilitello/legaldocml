package io.legaldocml.akn.element;

import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.PRESERVATION;

/**
 * The element preservation is the metadata property containing an arbitrary list of elements detailing the preservation
 * actions taken for the document is the respective level of the FRBR hierarchy.
 *
 * <pre>
 * 	 <xsd:element name="preservation" type="anyOtherType"/>
 * <pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Preservation extends AnyOtherType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_PRESERVATION = Buffers.address(PRESERVATION);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_PRESERVATION, 12);
        super.write(writer);
        writer.writeEnd(ADDRESS_PRESERVATION, 12);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return PRESERVATION;
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
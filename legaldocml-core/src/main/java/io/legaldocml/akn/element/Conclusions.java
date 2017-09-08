package io.legaldocml.akn.element;

import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.CONCLUSIONS;

/**
 * The element conclusion is used as a container of all concluding material (e.g. dates, signatures, formulas, etc.).
 *
 * <pre>
 *   <xsd:element name="conclusions" type="basicopt"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Conclusions extends Basicopt {


    /**
     * Memory address.
     */
    private static final long ADDRESS_CONCLUSIONS = Buffers.address(CONCLUSIONS);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_CONCLUSIONS, 11);
        super.write(writer);
        writer.writeEnd(ADDRESS_CONCLUSIONS, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return CONCLUSIONS;
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
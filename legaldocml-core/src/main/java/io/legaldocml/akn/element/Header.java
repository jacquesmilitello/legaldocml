package io.legaldocml.akn.element;

import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.HEADER;

/**
 * the element header is used as a container of all prefacing material of judgments (e.g. headers, formulas, etc.).
 *
 * <pre>
 *   <xsd:element name="header" type="blocksopt"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Header extends Blocksopt {

    /**
     * Memory address.
     */
    private static final long ADDRESS_HEADER = Buffers.address(HEADER);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_HEADER, 6);
        super.write(writer);
        writer.writeEnd(ADDRESS_HEADER, 6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return HEADER;
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
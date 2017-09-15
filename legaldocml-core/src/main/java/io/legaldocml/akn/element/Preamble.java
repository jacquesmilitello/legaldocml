package io.legaldocml.akn.element;

import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.PREAMBLE;

/**
 * The element preamble is used as a container of the text that opens the main body of the document as a preamble.
 *
 * <pre>
 * 	 <xsd:element name="preamble" type="preambleopt"/>
 * <pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Preamble extends Preambleopt implements PortionBodyTypeElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_PREAMBLE = Buffers.address(PREAMBLE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_PREAMBLE, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS_PREAMBLE, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return PREAMBLE;
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
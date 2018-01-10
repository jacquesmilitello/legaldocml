package io.legaldocml.akn.element;

import io.legaldocml.akn.AknElements;
import io.legaldocml.akn.group.HTMLblock;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element p is an HTML element and is used in Akoma Ntoso as in HTML, for the generic paragraph of text (a block).
 *
 * <pre>
 * 	 <xsd:element name="p" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class P extends InlineType implements LiElement, HTMLblock {

    /**
     * Memory address.
     */
    private static final long ADDRESS_P = Buffers.address(AknElements.P);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_P, 1);
        super.write(writer);
        writer.writeEnd(ADDRESS_P, 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return AknElements.P;
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
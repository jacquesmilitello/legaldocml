package io.legaldocml.akn.element;

import io.legaldocml.akn.group.AmendmentInline;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.CHANGE;

/**
 * The element change is an inline element that identifies the changes expressed in the two columns of an amendment
 * document.
 *
 * <pre>
 *   <xsd:element name="change" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Change extends InlineType implements AmendmentInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_CHANGE = Buffers.address(CHANGE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_CHANGE, 6);
        super.write(writer);
        writer.writeEnd(ADDRESS_CHANGE, 6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return CHANGE;
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
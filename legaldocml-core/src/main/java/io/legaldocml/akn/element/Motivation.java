package io.legaldocml.akn.element;

import io.legaldocml.akn.group.JudgmentBlock;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.MOTIVATION;

/**
 * This element is a structural container for the section of a judgment containing the motivation.
 *
 * <pre>
 * 	 <xsd:element name="motivation" type="maincontent"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Motivation extends MainContent implements JudgmentBlock {

    /**
     * Memory address.
     */
    private static final long ADDRESS_MOTIVATION = Buffers.address(MOTIVATION);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_MOTIVATION, 10);
        super.write(writer);
        writer.writeEnd(ADDRESS_MOTIVATION, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return MOTIVATION;
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
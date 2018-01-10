package io.legaldocml.akn.element;

import io.legaldocml.akn.group.JudgmentBlock;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.DECISION;

/**
 * This element is a structural container for the section of a judgment containing the decision.
 *
 * <pre>
 * 	 <xsd:element name="decision" type="maincontent"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Decision extends MainContent implements JudgmentBlock {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DECISION = Buffers.address(DECISION);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DECISION, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS_DECISION, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DECISION;
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
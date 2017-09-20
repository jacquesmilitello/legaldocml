package io.legaldocml.akn.element;

import io.legaldocml.akn.group.JudgmentBlock;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.REMEDIES;

/**
 * This element is a structural container for the section of a judgment containing the remedies.
 *
 * <pre>
 * 	 <xsd:element name="remedies" type="maincontent"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Remedies extends MainContent implements JudgmentBlock {

    /**
     * Memory address.
     */
    private static final long ADDRESS_REMEDIES = Buffers.address(REMEDIES);


    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_REMEDIES, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS_REMEDIES, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return REMEDIES;
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
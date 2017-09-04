package io.legaldocml.akn.element;

import io.legaldocml.akn.group.JudgmentBlock;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.ARGUMENTS;

/**
 * This element is a structural container for the section of a judgment containing the arguments.
 *
 * <pre>
 * 	 <xsd:element name="arguments" type="maincontent"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Arguments extends MainContent implements JudgmentBlock {

    /**
     * Memory address.
     */
    private static final long ADDRESS_ARGUMENTS = Buffers.address(ARGUMENTS);


    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_ARGUMENTS, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS_ARGUMENTS, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ARGUMENTS;
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
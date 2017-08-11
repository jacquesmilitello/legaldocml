package io.legaldocml.akn.element;

import io.legaldocml.akn.group.JudgmentBlock;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * This element is a structural container for the section of a judgment containing the background.
 * <p/>
 * <pre>
 * 	 <xsd:element name="background" type="maincontent"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Background extends MainContent implements JudgmentBlock {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "background";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);


    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 10);
        super.write(writer);
        writer.writeEnd(ADDRESS, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
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
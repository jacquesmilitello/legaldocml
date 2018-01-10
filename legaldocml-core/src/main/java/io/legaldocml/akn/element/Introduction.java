package io.legaldocml.akn.element;

import io.legaldocml.akn.group.JudgmentBlock;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.INTRODUCTION;

/**
 * This element is a structural container for the section of a judgment containing introductory material.
 *
 * <pre>
 * 	 <xsd:element name="introduction" type="maincontent"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Introduction extends MainContent implements JudgmentBlock {

    /**
     * Memory address.
     */
    private static final long ADDRESS_INTRODUCTION = Buffers.address(INTRODUCTION);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_INTRODUCTION, 12);
        super.write(writer);
        writer.writeEnd(ADDRESS_INTRODUCTION, 12);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return INTRODUCTION;
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
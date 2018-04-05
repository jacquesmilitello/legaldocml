package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANhier;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.CHAPTER;

/**
 * This element is a hierarchical container called "chapter" either explicitly or due to the local tradition.
 *
 * <pre>
 * 	 <xsd:element name="chapter" type="hierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Chapter extends Hierarchy implements ANhier {

    /**
     * Memory address.
     */
    private static final long ADDRESS_CHAPTER = Buffers.address(CHAPTER);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_CHAPTER, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS_CHAPTER, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return CHAPTER;
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
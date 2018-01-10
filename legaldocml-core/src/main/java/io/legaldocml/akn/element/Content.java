package io.legaldocml.akn.element;

import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.CONTENT;

/**
 * The element content is the final container in a hierarchy, and is where the blocks of text of the content of the
 * structure are finally specified.
 *
 * <pre>
 * 	 <xsd:element name="content" type="blocksopt"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Content extends Blocksreq implements SubFlowStructureElement, PopupStructureElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_CONTENT = Buffers.address(CONTENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_CONTENT, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS_CONTENT, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return CONTENT;
    }

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void accept(ContentVisitor visitor) {
//        if (VisitorStatus.CONTINUE == visitor.visitBegin(this)) {
//            super.accept(visitor);
//            visitor.visitEnd(this);
//        }
//    }
}
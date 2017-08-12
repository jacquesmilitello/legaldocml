package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element content is the final container in a hierarchy, and is where the blocks of text of the content of the
 * structure are finally specified.
 *
 * <pre>
 * 	 &lt;xsd:element name="content" type="blocksopt"/&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Content extends Blocksreq implements SubFlowStructureElement, PopupStructureElement {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "content";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
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
package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANblock;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element blockList is used as in a block context as a container of many individual item elements to be treated as
 * in a list.
 * <p/>
 * <pre>
 *   <xsd:element name="blockList" type="blockListType">
 * </pre>
 */
public final class BlockList extends BlockListType implements ANblock {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "blockList";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS, 9);
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
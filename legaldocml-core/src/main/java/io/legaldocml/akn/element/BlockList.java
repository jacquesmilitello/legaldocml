package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANblock;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.BLOCK_LIST;

/**
 * The element blockList is used as in a block context as a container of many individual item elements to be treated as
 * in a list.
 *
 * <pre>
 *   <xsd:element name="blockList" type="blockListType">
 * </pre>
 */
public final class BlockList extends BlockListType implements ANblock {

    /**
     * Memory address.
     */
    private static final long ADDRESS_BLOCK_LIST = Buffers.address(BLOCK_LIST);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_BLOCK_LIST, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS_BLOCK_LIST, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return BLOCK_LIST;
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
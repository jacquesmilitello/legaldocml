package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANblock;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.BLOCK_CONTAINER;

/**
 * The element blockContainer is used as a container of many individual block elements in a block context
 *
 * <pre>
 * 	<xsd:element name="blockContainer" type="blockContainerType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class BlockContainer extends BlockContainerType implements ANblock {

    /**
     * Memory address.
     */
    private static final long ADDRESS_BLOCK_CONTAINER = Buffers.address(BLOCK_CONTAINER);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_BLOCK_CONTAINER, 14);
        super.write(writer);
        writer.writeEnd(ADDRESS_BLOCK_CONTAINER, 14);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return BLOCK_CONTAINER;
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

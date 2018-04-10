package io.legaldocml.akn.element;

import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.ITEM;

/**
 * The element item is a container belonging to a blockList.
 * <pre>
 * 	 <xsd:element name="item" type="itemType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Item extends ItemType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_ITEM = Buffers.address(ITEM);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_ITEM, 4);
        super.write(writer);
        writer.writeEnd(ADDRESS_ITEM, 4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ITEM;
    }

    @Override
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            super.accept(visitor);
            visitor.visitLeave(this);
        }
    }
}
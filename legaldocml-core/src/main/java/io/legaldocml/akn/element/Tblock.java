package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANblock;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.TBLOCK;

/**
 * The element tblock (titled block) is used to specify a container for blocks introduced by heading elements, similarly
 * to a hierarchical structure.
 * <pre>
 * 	 <xsd:element name="tblock" type="itemType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Tblock extends ItemType implements ANblock {


    /**
     * Memory address.
     */
    private static final long ADDRESS_TBLOCK = Buffers.address(TBLOCK);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_TBLOCK, 6);
        super.write(writer);
        writer.writeEnd(ADDRESS_TBLOCK, 6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return TBLOCK;
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
package io.legaldocml.akn.element;

import io.legaldocml.akn.group.BlockElements;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.FOREIGN;

/**
 * The element foreign is a generic container for elements not belonging to the Akoma Ntoso namespace (e.g.,
 * mathematical formulas). It is a block element and thus can be placed in a container.
 *
 * <pre>
 *   <xsd:element name="foreign" type="anyOtherType"/>
 * <pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Foreign extends AnyOtherType implements BlockElements {

    private static final long ADDRESS_FOREIGN = Buffers.address(FOREIGN);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_FOREIGN, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS_FOREIGN, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return FOREIGN;
    }

}
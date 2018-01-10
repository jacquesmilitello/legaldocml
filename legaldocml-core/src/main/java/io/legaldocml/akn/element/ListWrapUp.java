package io.legaldocml.akn.element;

import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.LIST_WRAP_UP;

/**
 * The element listWrapUp is an optional element of blockList after all items of the list itself.
 *
 * <pre>
 *   <xsd:element name="listWrapUp" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ListWrapUp extends InlineType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_LIST_WRAP_UP = Buffers.address(LIST_WRAP_UP);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_LIST_WRAP_UP, 10);
        super.write(writer);
        writer.writeEnd(ADDRESS_LIST_WRAP_UP, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return LIST_WRAP_UP;
    }

}
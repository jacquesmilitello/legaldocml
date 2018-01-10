package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANhier;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.LIST;

/**
 * This element is a hierarchical container called "list" either explicitly or due to the local tradition.
 *
 * <pre>
 *   <xsd:element name="list" type="hierarchy"/>
 * </pre>
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 *
 */
public final class List extends Hierarchy implements ANhier {

    /**
     * Memory address.
     */
    private static final long ADDRESS_LIST = Buffers.address(LIST);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_LIST, 4);
        super.write(writer);
        writer.writeEnd(ADDRESS_LIST, 4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return LIST;
    }

}
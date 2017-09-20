package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANhier;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.TITLE;


/**
 * This element is a hierarchical container called "title" either explicitly or due to the local tradition.
 *
 * <pre>
 *   <xsd:element name="title" type="hierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Title extends Hierarchy implements ANhier {

    /**
     * Memory address.
     */
    private static final long ADDRESS_TITLE = Buffers.address(TITLE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_TITLE, 5);
        super.write(writer);
        writer.writeEnd(ADDRESS_TITLE, 5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return TITLE;
    }

}
package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element listIntroduction is an optional element of blockList before any item of the list itself.
 *
 * <pre>
 *   &lt;xsd:element name="listIntroduction" type="inline"/&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ListIntroduction extends InlineType {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "listIntroduction";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 16);
        super.write(writer);
        writer.writeEnd(ADDRESS, 16);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}
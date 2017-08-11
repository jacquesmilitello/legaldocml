package io.legaldocml.akn.element;

import io.legaldocml.akn.group.AmendmentBlock;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * This element is a structural container for the section of an amendment containing the actual amendment text.
 * <p/>
 * <pre>
 *   <xsd:element name="amendmentContent" type="blocksopt"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AmendmentContent extends Blocksopt implements AmendmentBlock {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "amendmentContent";

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
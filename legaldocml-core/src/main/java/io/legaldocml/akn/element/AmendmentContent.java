package io.legaldocml.akn.element;

import io.legaldocml.akn.AknElements;
import io.legaldocml.akn.group.AmendmentBlock;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

/**
 * This element is a structural container for the section of an amendment containing the actual amendment text.
 *
 * <pre>
 *   <xsd:element name="amendmentContent" type="blocksopt"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AmendmentContent extends Blocksopt implements AmendmentBlock {

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(AknElements.AMENDMENT_CONTENT);

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
        return AknElements.AMENDMENT_CONTENT;
    }

}
package io.legaldocml.akn.element;

import io.legaldocml.akn.group.AmendmentBlock;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.AMENDMENT_JUSTIFICATION;

/**
 * This element is a structural container for the section of an amendment containing the justification.
 *
 * <pre>
 * 	 <xsd:element name="amendmentJustification" type="blocksopt"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AmendmentJustification extends Blocksopt implements AmendmentBlock {

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(AMENDMENT_JUSTIFICATION);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 22);
        super.write(writer);
        writer.writeEnd(ADDRESS, 22);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return AMENDMENT_JUSTIFICATION;
    }

}
package io.legaldocml.akn.element;

import io.legaldocml.akn.group.AmendmentBlock;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.AMENDMENT_REFERENCE;

/**
 * This element is a structural container for the section of an amendment containing the reference.
 *
 * <pre>
 * 	 <xsd:element name="amendmentReference" type="blocksopt"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AmendmentReference extends Blocksopt implements AmendmentBlock {

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(AMENDMENT_REFERENCE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 18);
        super.write(writer);
        writer.writeEnd(ADDRESS, 18);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return AMENDMENT_REFERENCE;
    }

}
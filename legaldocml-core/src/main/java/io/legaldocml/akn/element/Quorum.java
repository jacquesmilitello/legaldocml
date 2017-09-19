package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.QUORUM;

/**
 * The element quorum is a metadata container containing the value of a quorum in a vote or a quorum verification.
 *
 * <pre>
 *   <xsd:element name="quorum" type="countType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Quorum extends CountType implements ParliamentaryAnalysisTypeElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_QUORUM = Buffers.address(QUORUM);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_QUORUM, 6);
        super.write(writer);
        writer.writeEnd(ADDRESS_QUORUM, 6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return QUORUM;
    }

}
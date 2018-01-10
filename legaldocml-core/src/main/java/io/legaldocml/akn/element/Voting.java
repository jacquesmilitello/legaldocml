package io.legaldocml.akn.element;

import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.VOTING;

/**
 * The element voting is a metadata container containing information about an event of a vote happened within a debate.
 *
 * <pre>
 *   <xsd:element name="voting" type="parliamentaryAnalysisType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Voting extends ParliamentaryAnalysisType implements ParliamentaryAnalysisElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_VOTING = Buffers.address(VOTING);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_VOTING, 6);
        super.write(writer);
        writer.writeEnd(ADDRESS_VOTING, 6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return VOTING;
    }

}
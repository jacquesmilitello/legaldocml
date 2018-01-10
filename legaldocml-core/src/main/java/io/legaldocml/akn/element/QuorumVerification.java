package io.legaldocml.akn.element;

import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.QUORUM_VERIFICATION;

/**
 * The element quorumVerification is a metadata container containing information about an event of quorum verification
 * happened within a debate.
 *
 * <pre>
 * 	 <xsd:element name="quorumVerification" type="parliamentaryAnalysisType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class QuorumVerification extends ParliamentaryAnalysisType implements ParliamentaryAnalysisElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_QUORUM_VERIFICATION = Buffers.address(QUORUM_VERIFICATION);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_QUORUM_VERIFICATION, 18);
        super.write(writer);
        writer.writeEnd(ADDRESS_QUORUM_VERIFICATION, 18);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return QUORUM_VERIFICATION;
    }

}

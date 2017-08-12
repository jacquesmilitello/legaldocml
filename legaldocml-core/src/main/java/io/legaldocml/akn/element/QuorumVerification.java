package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element quorumVerification is a metadata container containing information about an event of quorum verification
 * happened within a debate.
 *
 * <pre>
 * 	 &lt;xsd:element name="quorumVerification" type="parliamentaryAnalysisType"/&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class QuorumVerification extends ParliamentaryAnalysisType implements ParliamentaryAnalysisElement {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "quorumVerification";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

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
        return ELEMENT;
    }

}

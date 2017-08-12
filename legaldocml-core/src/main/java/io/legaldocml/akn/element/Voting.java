package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element voting is a metadata container containing information about an event of a vote happened within a debate.
 *
 * <pre>
 *   &lt;xsd:element name="voting" type="parliamentaryAnalysisType"/&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Voting extends ParliamentaryAnalysisType implements ParliamentaryAnalysisElement {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "voting";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 6);
        super.write(writer);
        writer.writeEnd(ADDRESS, 6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}
package io.legaldocml.akn.element;

import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

/**
 * The element count is a metadata container containing the value of a count in a vote or a quorum verification.
 * <p/>
 * <pre>
 *   <xsd:element name="count" type="countType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Count extends CountType implements ParliamentaryAnalysisTypeElement {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "count";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 5);
        super.write(writer);
        writer.writeEnd(ADDRESS, 5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }


}
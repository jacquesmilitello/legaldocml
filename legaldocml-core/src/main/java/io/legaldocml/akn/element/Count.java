package io.legaldocml.akn.element;

import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.COUNT;

/**
 * The element count is a metadata container containing the value of a count in a vote or a quorum verification.
 *
 * <pre>
 *   <xsd:element name="count" type="countType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Count extends CountType implements ParliamentaryAnalysisTypeElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_COUNT = Buffers.address(COUNT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_COUNT, 5);
        super.write(writer);
        writer.writeEnd(ADDRESS_COUNT, 5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return COUNT;
    }


}
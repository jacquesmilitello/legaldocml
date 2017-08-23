package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element recount is a metadata container containing information about an event of a recount happened within a
 * debate.
 *
 * <pre>
 *   <xsd:element name="recount" type="parliamentaryAnalysisType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Recount extends ParliamentaryAnalysisType implements ParliamentaryAnalysisElement {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "recount";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }
}

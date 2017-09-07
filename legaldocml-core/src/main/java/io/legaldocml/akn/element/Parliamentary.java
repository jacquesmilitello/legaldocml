package io.legaldocml.akn.element;

import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.PARLIAMENTARY;

/**
 * The element parliamentary is a metadata container of the analysis of the events of a debate.
 *
 * <pre>
 * 	 <xsd:element name="parliamentary" type="parliamentaryAnalysis"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Parliamentary extends ParliamentaryAnalysis {


    /**
     * Memory address.
     */
    private static final long ADDRESS_PARLIAMENTARY = Buffers.address(PARLIAMENTARY);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_PARLIAMENTARY, 13);
        super.write(writer);
        writer.writeEnd(ADDRESS_PARLIAMENTARY, 13);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return PARLIAMENTARY;
    }

}
package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.JUDICIAL;

/**
 * The element judicial is a metadata container of the analysis of the judicial arguments of a judgment.
 *
 *
 * <pre>
 * 	 <xsd:element name="judicial" type="judicialArguments">
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Judicial extends JudicialArguments {

    /**
     * Memory address.
     */
    private static final long ADDRESS_JUDICIAL = Buffers.address(JUDICIAL);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_JUDICIAL, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS_JUDICIAL, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return JUDICIAL;
    }

}
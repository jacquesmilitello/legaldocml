package io.legaldocml.akn.element;

import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.EFFICACY;

/**
 * The element efficacy is a metadata element specifying the period of the efficacy modification.
 *
 * <pre>
 *   <xsd:element name="efficacy" type="periodType">
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Efficacy extends PeriodType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_EFFICACY = Buffers.address(EFFICACY);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_EFFICACY, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS_EFFICACY, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return EFFICACY;
    }

}

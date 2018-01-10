package io.legaldocml.akn.element;

import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.DURATION;

/**
 * The element duration is a metadata element specifying the period of the duration modification.
 *
 * <pre>
 *   <xsd:element name="duration" type="periodType">
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Duration extends PeriodType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DURATION = Buffers.address(DURATION);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DURATION, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS_DURATION, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DURATION;
    }

}

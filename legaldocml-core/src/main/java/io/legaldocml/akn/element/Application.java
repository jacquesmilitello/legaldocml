package io.legaldocml.akn.element;

import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.APPLICATION;

/**
 * The element application is a metadata element specifying the period of the application modification.
 *
 * <pre>
 *   <xsd:element name="application" type="periodType">
 * </pre>
 */
public final class Application extends PeriodType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_APPLICATION = Buffers.address(APPLICATION);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_APPLICATION, 11);
        super.write(writer);
        writer.writeEnd(ADDRESS_APPLICATION, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return APPLICATION;
    }

}

package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element FRBRItem is the metadata container of identifying properties related to the Item level according to the
 * FRBR hierarchy.
 * <p/>
 * <pre>
 *   <xsd:element name="FRBRItem" type="coreProperties"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRItem extends CoreProperties {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "FRBRItem";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}
package io.legaldocml.akn.element;

import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.FRBR_ITEM;

/**
 * The element FRBRItem is the metadata container of identifying properties related to the Item level according to the
 * FRBR hierarchy.
 *
 * <pre>
 *   <xsd:element name="FRBRItem" type="coreProperties"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRItem extends CoreProperties {

    /**
     * Memory address.
     */
    private static final long ADDRESS_FRBR_ITEM = Buffers.address(FRBR_ITEM);

    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_FRBR_ITEM, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS_FRBR_ITEM, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return FRBR_ITEM;
    }

}
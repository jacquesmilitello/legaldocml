package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.RECITAL;

/**
 * The element recital is the individual element of the preface that is called recital.
 *
 * <pre>
 *   <xsd:element name="recital" type="itemType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Recital extends ItemType implements PopupStructureElement, SubFlowStructureElement, PortionBodyTypeElement, ReciltalHierarchyElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_RECITAL = Buffers.address(RECITAL);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_RECITAL, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS_RECITAL, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return RECITAL;
    }


}
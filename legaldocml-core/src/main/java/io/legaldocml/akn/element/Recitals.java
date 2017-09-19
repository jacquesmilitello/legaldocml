package io.legaldocml.akn.element;

import io.legaldocml.akn.group.PreambleContainers;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.RECITALS;

/**
 * The element recitals is the section of the preamble that contains recitals.
 *
 * <pre>
 *   <xsd:element name="recitals" type="recitalHierarchy">
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Recitals extends RecitalHierarchy implements PreambleContainers, PopupStructureElement, SubFlowStructureElement, PortionBodyTypeElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_RECITALS = Buffers.address(RECITALS);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_RECITALS, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS_RECITALS, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return RECITALS;
    }

}
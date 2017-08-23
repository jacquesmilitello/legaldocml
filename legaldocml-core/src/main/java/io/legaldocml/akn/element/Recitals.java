package io.legaldocml.akn.element;

import io.legaldocml.akn.group.PreambleContainers;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

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
     * XML tag element name.
     */
    public static final String ELEMENT = "recitals";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
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
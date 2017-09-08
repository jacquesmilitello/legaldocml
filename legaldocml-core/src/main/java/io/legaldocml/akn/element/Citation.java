package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.CITATION;

/**
 * The element citation is the individual element of the preface that is called citation.
 *
 * <pre>
 * 	 <xsd:element name="citation" type="itemType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Citation extends ItemType implements PopupStructureElement, SubFlowStructureElement, PortionBodyTypeElement, CitationHierarchyElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_CITATION = Buffers.address(CITATION);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_CITATION, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS_CITATION, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return CITATION;
    }


}
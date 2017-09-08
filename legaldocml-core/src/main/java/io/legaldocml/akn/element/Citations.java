package io.legaldocml.akn.element;

import io.legaldocml.akn.group.PreambleContainers;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.CITATIONS;

/**
 * The element citation is the individual element of the preface that is called citation.
 *
 * <pre>
 *   <xsd:element name="citations" type="citationHierarchy">
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Citations extends CitationHierarchy implements PreambleContainers, PopupStructureElement, SubFlowStructureElement, PortionBodyTypeElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_CITATIONS = Buffers.address(CITATIONS);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_CITATIONS, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS_CITATIONS, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return CITATIONS;
    }

}
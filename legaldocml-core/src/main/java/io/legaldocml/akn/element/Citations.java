package io.legaldocml.akn.element;

import io.legaldocml.akn.group.PreambleContainers;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element citation is the individual element of the preface that is called citation.
 *
 * <pre>
 *   &lt;xsd:element name="citations" type="citationHierarchy"&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Citations extends CitationHierarchy implements PreambleContainers, PopupStructureElement, SubFlowStructureElement, PortionBodyTypeElement {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "citations";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}
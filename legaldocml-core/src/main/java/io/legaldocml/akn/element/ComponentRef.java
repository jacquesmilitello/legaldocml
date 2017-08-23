package io.legaldocml.akn.element;

import io.legaldocml.akn.CollectionBodyElement;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element componentRef is a reference to a separate manifestation-level resource that holds the content of the
 * component of the document not physically placed at the position specified. Actual resources can either be external
 * (e.g. in the package or even in a different position) or internal (within the components element).
 *
 * <pre>
 *   <xsd:element name="componentRef" type="srcType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ComponentRef extends SrcType implements BodyTypeElement, HierarchyElement, PopupStructureElement, MainContentElement, CollectionBodyElement, AltHierarchyElement, SubFlowStructureElement, ReciltalHierarchyElement, CitationHierarchyElement, BlockContainerTypeElement {

    /**
     * Xml Element Name.
     */
    public static final String ELEMENT = "componentRef";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 12);
        super.write(writer);
        writer.writeEnd(ADDRESS, 12);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}
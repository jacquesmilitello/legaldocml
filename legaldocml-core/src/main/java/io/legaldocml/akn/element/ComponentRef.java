package io.legaldocml.akn.element;

import static io.legaldocml.akn.AknElements.COMPONENT_REF;

import java.io.IOException;

import io.legaldocml.akn.CollectionBodyElement;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

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
     * Memory address.
     */
    private static final long ADDRESS_COMPONENT_REF = Buffers.address(COMPONENT_REF);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_COMPONENT_REF, 12);
        super.write(writer);
        writer.writeEnd(ADDRESS_COMPONENT_REF, 12);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return COMPONENT_REF;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
       visitor.visit(this);
    }

}
package io.legaldocml.akn.element;


import io.legaldocml.akn.DocContainerTypeElement;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.DOCUMENT_REF;

/**
 * the element documentRef is a reference to a separate work- or expression-level resource that should be placed in this
 * position. Actual resources are external (e.g. in the package or even in a different position) and are (an expression
 * or any expression of) a separate Work.
 *
 * <pre>
 *   <xsd:element name="documentRef" type="linkType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DocumentRef extends LinkType implements DocContainerTypeElement, SubFlowStructureElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DOCUMENT_REF = Buffers.address(DOCUMENT_REF);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DOCUMENT_REF, 11);
        super.write(writer);
        writer.writeEnd(ADDRESS_DOCUMENT_REF, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DOCUMENT_REF;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        visitor.visit(this);
    }
}
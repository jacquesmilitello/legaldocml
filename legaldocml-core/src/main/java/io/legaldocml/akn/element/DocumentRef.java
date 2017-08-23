package io.legaldocml.akn.element;


import io.legaldocml.akn.DocContainerTypeElement;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

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
public final class DocumentRef extends LinkType implements DocContainerTypeElement {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "documentRef";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 11);
        super.write(writer);
        writer.writeEnd(ADDRESS, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        visitor.visit(this);
    }
}
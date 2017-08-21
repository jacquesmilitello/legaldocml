package io.legaldocml.akn.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * Element bill is used for describing the structure and content of a bill.
 *
 * <pre>
 *   &lt;xsd:element name="bill" type="hierarchicalStructure"&gt;
 *     &lt;xsd:unique name="eId-bill"&gt;
 *       &lt;xsd:selector xpath=".//*"/&gt;
 *       &lt;xsd:field xpath="@eId"/&gt;
 *     &lt;xsd:unique&gt;
 *     &lt;xsd:unique name="GUID-bill"&gt;
 *       &lt;xsd:selector xpath=".//*"/&gt;
 *       &lt;xsd:field xpath="@GUID"/&gt;
 *     &lt;xsd:unique&gt;
 *   &lt;xsd:element&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Bill extends HierarchicalStructure implements DocumentType {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "bill";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 4);
        super.write(writer);
        writer.writeEnd(ADDRESS, 4);
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
        if (visitor.visitEnter(this)) {
            super.accept(visitor);
            visitor.visitLeave(this);
        }
    }

}
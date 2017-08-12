package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element recital is the individual element of the preface that is called recital.
 *
 * <pre>
 *   &lt;xsd:element name="recital" type="itemType"/&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Recital extends ItemType implements PopupStructureElement, SubFlowStructureElement, PortionBodyTypeElement, ReciltalHierarchyElement {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "recital";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }


}
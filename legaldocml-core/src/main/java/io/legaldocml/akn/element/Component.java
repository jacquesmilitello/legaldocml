package io.legaldocml.akn.element;

import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.COMPONENT;

/**
 * The element component is a container of a subdocument specified in a composite document.
 *
 * <pre>
 *   <xsd:element name="component" type="docContainerType">
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Component extends DocContainerType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_COMPONENT = Buffers.address(COMPONENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_COMPONENT, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS_COMPONENT, 9);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return COMPONENT;
    }

}
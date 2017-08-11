package io.legaldocml.akn.element;

import io.legaldocml.akn.group.BasicContainers;
import io.legaldocml.akn.group.ContainerElements;
import io.legaldocml.akn.group.PreambleContainers;
import io.legaldocml.akn.group.PrefaceContainers;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element container is a generic element for a container.
 * <p/>
 * <pre>
 * 	 <xsd:element name="container" type="containerType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Container extends ContainerType implements BasicContainers, PrefaceContainers, ContainerElement,
        ContainerElements, PreambleContainers {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "container";

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
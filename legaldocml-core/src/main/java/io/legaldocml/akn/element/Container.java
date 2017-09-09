package io.legaldocml.akn.element;

import io.legaldocml.akn.group.BasicContainers;
import io.legaldocml.akn.group.ContainerElements;
import io.legaldocml.akn.group.PreambleContainers;
import io.legaldocml.akn.group.PrefaceContainers;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.CONTAINER;

/**
 * The element container is a generic element for a container.
 *
 * <pre>
 * 	 <xsd:element name="container" type="containerType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Container extends ContainerType implements BasicContainers, PrefaceContainers, ContainerElement,
        ContainerElements, PreambleContainers {

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(CONTAINER);

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
        return CONTAINER;
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
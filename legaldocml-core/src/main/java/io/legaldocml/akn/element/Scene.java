package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANcontainers;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.SCENE;

/**
 * The element scene is a container of descriptions of the scene elements happening in a given moment during a debate
 * (e.g., applauses).
 *
 * <pre>
 *   <xsd:element name="scene" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Scene extends InlineType implements ANcontainers {

    /**
     * Memory address.
     */
    private static final long ADDRESS_SCENE = Buffers.address(SCENE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_SCENE, 5);
        super.write(writer);
        writer.writeEnd(ADDRESS_SCENE, 5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return SCENE;
    }

}
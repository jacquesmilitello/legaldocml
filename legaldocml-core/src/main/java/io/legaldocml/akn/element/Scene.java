package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANcontainers;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element scene is a container of descriptions of the scene elements happening in a given moment during a debate
 * (e.g., applauses).
 * <p/>
 * <pre>
 *   <xsd:element name="scene" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Scene extends InlineType implements ANcontainers {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "scene";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 5);
        super.write(writer);
        writer.writeEnd(ADDRESS, 5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}
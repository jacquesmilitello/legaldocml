package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.DESTINATION;

/**
 * The element destination is a metadata element specifying the IRI of the destination of the modification.
 *
 * <pre>
 *   <xsd:element name="destination" type="argumentType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Destination extends ArgumentType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DESTINATION = Buffers.address(DESTINATION);

    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DESTINATION, 11);
        super.write(writer);
        writer.writeEnd(ADDRESS_DESTINATION, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DESTINATION;
    }
}
package io.legaldocml.akn.element;

import io.legaldocml.akn.group.SpeechSection;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.COMMUNICATION;

/**
 * This element is a structural container for parts of a debates that contain communications from the house.
 *
 * <pre>
 *  <xsd:element name="communication" type="althierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Communication extends AltHierarchy implements SpeechSection {

    /**
     * Memory address.
     */
    private static final long ADDRESS_COMMUNICATION = Buffers.address(COMMUNICATION);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_COMMUNICATION, 13);
        super.write(writer);
        writer.writeEnd(ADDRESS_COMMUNICATION, 13);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return COMMUNICATION;
    }

}
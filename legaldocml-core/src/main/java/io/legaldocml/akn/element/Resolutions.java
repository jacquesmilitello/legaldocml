package io.legaldocml.akn.element;

import io.legaldocml.akn.group.SpeechSection;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.RESOLUTIONS;

/**
 * This element is a structural container for parts of a debates that contain resolutions.
 *
 * <pre>
 *   <xsd:element name="resolutions" type="althierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Resolutions extends AltHierarchy implements SpeechSection {

    /**
     * Memory address.
     */
    private static final long ADDRESS_RESOLUTIONS = Buffers.address(RESOLUTIONS);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_RESOLUTIONS, 11);
        super.write(writer);
        writer.writeEnd(ADDRESS_RESOLUTIONS, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return RESOLUTIONS;
    }

}
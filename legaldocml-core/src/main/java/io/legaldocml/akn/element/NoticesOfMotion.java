package io.legaldocml.akn.element;

import io.legaldocml.akn.group.SpeechSection;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.NOTICES_OF_MOTION;

/**
 * This element is a structural container for parts of a debates that are relevant to the notices of motions.
 *
 * <pre>
 *  <xsd:element name="noticesOfMotion" type="althierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class NoticesOfMotion extends AltHierarchy implements SpeechSection {

    /**
     * Memory address.
     */
    private static final long ADDRESS_NOTICES_OF_MOTION = Buffers.address(NOTICES_OF_MOTION);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_NOTICES_OF_MOTION, 15);
        super.write(writer);
        writer.writeEnd(ADDRESS_NOTICES_OF_MOTION, 15);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return NOTICES_OF_MOTION;
    }

}
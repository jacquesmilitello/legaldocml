package io.legaldocml.akn.element;


import io.legaldocml.akn.group.SpeechSection;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.PRAYERS;

/**
 * This element is a structural container for parts of a debates that contain prayers.
 *
 * <pre>
 * 	 <xsd:element name="prayers" type="althierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Prayers extends AltHierarchy implements SpeechSection {

    /**
     * Memory address.
     */
    private static final long ADDRESS_PRAYERS = Buffers.address(PRAYERS);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_PRAYERS, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS_PRAYERS, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return PRAYERS;
    }

}
package io.legaldocml.akn.element;

import io.legaldocml.akn.group.SpeechSection;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.NATIONAL_INTEREST;

/**
 * This element is a structural container for parts of a debates that contain resolutions.
 *
 * <pre>
 *   <xsd:element name="nationalInterest" type="althierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class NationalInterest extends AltHierarchy implements SpeechSection {

    /**
     * Memory address.
     */
    private static final long ADDRESS_NATIONAL_INTEREST = Buffers.address(NATIONAL_INTEREST);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_NATIONAL_INTEREST, 16);
        super.write(writer);
        writer.writeEnd(ADDRESS_NATIONAL_INTEREST, 16);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return NATIONAL_INTEREST;
    }

}
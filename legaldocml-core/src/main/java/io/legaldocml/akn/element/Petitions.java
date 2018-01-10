package io.legaldocml.akn.element;

import io.legaldocml.akn.group.SpeechSection;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.PETITIONS;

/**
 * This element is a structural container for parts of a debates that are relevant to petitions.
 *
 * <pre>
 * 	 <xsd:element name="petitions" type="althierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Petitions extends AltHierarchy implements SpeechSection {

    /**
     * Memory address.
     */
    private static final long ADDRESS_PETITIONS = Buffers.address(PETITIONS);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_PETITIONS, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS_PETITIONS, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return PETITIONS;
    }

}

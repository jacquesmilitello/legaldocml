package io.legaldocml.akn.element;

import io.legaldocml.akn.group.SpeechSection;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.PROCEDURAL_MOTIONS;

/**
 * This element is a structural container for parts of a debates that are relevant to procedural motions.
 *
 * <pre>
 * 	 <xsd:element name="proceduralMotions" type="althierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ProceduralMotions extends AltHierarchy implements SpeechSection {

    /**
     * Memory address.
     */
    private static final long ADDRESS_PROCEDURAL_MOTIONS = Buffers.address(PROCEDURAL_MOTIONS);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_PROCEDURAL_MOTIONS, 17);
        super.write(writer);
        writer.writeEnd(ADDRESS_PROCEDURAL_MOTIONS, 17);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return PROCEDURAL_MOTIONS;
    }

}

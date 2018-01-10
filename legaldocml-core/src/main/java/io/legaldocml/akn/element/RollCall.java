package io.legaldocml.akn.element;

import io.legaldocml.akn.group.SpeechSection;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.ROLL_CALL;

/**
 * This element is a structural container for parts of a debates that contain a roll call of individuals.
 *
 * <pre>
 * 	 <xsd:element name="rollCall" type="althierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class RollCall extends AltHierarchy implements SpeechSection {

    /**
     * Memory address.
     */
    private static final long ADDRESS_ROLL_CALL = Buffers.address(ROLL_CALL);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_ROLL_CALL, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS_ROLL_CALL, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ROLL_CALL;
    }


}
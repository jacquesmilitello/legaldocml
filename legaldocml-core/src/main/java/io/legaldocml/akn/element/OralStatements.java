package io.legaldocml.akn.element;

import io.legaldocml.akn.group.SpeechSection;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.ORAL_STATEMENTS;

/**
 * This element is a structural container for parts of a debates that contain oral statements by participants.
 *
 * <pre>
 * 	 <xsd:element name="oralStatements" type="althierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class OralStatements extends AltHierarchy implements SpeechSection {

    /**
     * Memory address.
     */
    private static final long ADDRESS_ORAL_STATEMENTS = Buffers.address(ORAL_STATEMENTS);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_ORAL_STATEMENTS, 14);
        super.write(writer);
        writer.writeEnd(ADDRESS_ORAL_STATEMENTS, 14);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ORAL_STATEMENTS;
    }


}
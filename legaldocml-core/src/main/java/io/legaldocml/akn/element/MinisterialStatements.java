package io.legaldocml.akn.element;

import io.legaldocml.akn.group.SpeechSection;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.MINISTERIAL_STATEMENTS;

/**
 * This element is a structural container for parts of a debates that contain written statements by participants.
 *
 * <pre>
 * 	 <xsd:element name="ministerialStatements" type="althierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class MinisterialStatements extends AltHierarchy implements SpeechSection {

    /**
     * Memory address.
     */
    private static final long ADDRESS_MINISTERIAL_STATEMENTS = Buffers.address(MINISTERIAL_STATEMENTS);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_MINISTERIAL_STATEMENTS, 21);
        super.write(writer);
        writer.writeEnd(ADDRESS_MINISTERIAL_STATEMENTS, 21);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return MINISTERIAL_STATEMENTS;
    }

}

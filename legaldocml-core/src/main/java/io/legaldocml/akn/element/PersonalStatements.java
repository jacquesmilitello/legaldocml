package io.legaldocml.akn.element;

import io.legaldocml.akn.group.SpeechSection;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.PERSONAL_STATEMENTS;

/**
 * This element is a structural container for parts of a debates that contain written statements by participants.
 *
 * <pre>
 * 	 <xsd:element name="personalStatements" type="althierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class PersonalStatements extends AltHierarchy implements SpeechSection {

    /**
     * Memory address.
     */
    private static final long ADDRESS_PERSONAL_STATEMENTS = Buffers.address(PERSONAL_STATEMENTS);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_PERSONAL_STATEMENTS, 18);
        super.write(writer);
        writer.writeEnd(ADDRESS_PERSONAL_STATEMENTS, 18);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return PERSONAL_STATEMENTS;
    }

}

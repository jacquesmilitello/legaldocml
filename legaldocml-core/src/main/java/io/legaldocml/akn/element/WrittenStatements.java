package io.legaldocml.akn.element;

import io.legaldocml.akn.group.SpeechSection;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.WRITTEN_STATEMENTS;

/**
 * This element is a structural container for parts of a debates that contain written statements by participants.
 *
 * <pre>
 * 	 <xsd:element name="writtenStatements" type="althierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class WrittenStatements extends AltHierarchy implements SpeechSection {

    /**
     * Memory address.
     */
    private static final long ADDRESS_WRITTEN_STATEMENTS = Buffers.address(WRITTEN_STATEMENTS);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_WRITTEN_STATEMENTS, 17);
        super.write(writer);
        writer.writeEnd(ADDRESS_WRITTEN_STATEMENTS, 17);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return WRITTEN_STATEMENTS;
    }

}

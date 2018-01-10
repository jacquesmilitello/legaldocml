package io.legaldocml.akn.element;

import io.legaldocml.akn.group.SpeechSection;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.QUESTIONS;

/**
 * this element is a structural container for parts of a debates that are relevant to questions.
 *
 * <pre>
 *  <xsd:element name="questions" type="althierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Questions extends AltHierarchy implements SpeechSection {

    /**
     * Memory address.
     */
    private static final long ADDRESS_QUESTIONS = Buffers.address(QUESTIONS);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_QUESTIONS, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS_QUESTIONS, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return QUESTIONS;
    }

}
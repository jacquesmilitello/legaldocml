package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANcontainers;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.ANSWER;

/**
 * The element answer is a container of a single official answer to a question.
 *
 * <pre>
 * 	 <xsd:element name="answer" type="speechType"/>
 * </pre>
 */
public final class Answer extends SpeechType implements ANcontainers {

    /**
     * Memory address.
     */
    private static final long ADDRESS_ANSWER = Buffers.address(ANSWER);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_ANSWER, 6);
        super.write(writer);
        writer.writeEnd(ADDRESS_ANSWER, 6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ANSWER;
    }

}
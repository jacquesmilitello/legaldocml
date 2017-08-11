package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANcontainers;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element answer is a container of a single official answer to a question.
 * <p/>
 * <pre>
 * 	 <xsd:element name="answer" type="speechType"/>
 * </pre>
 */
public final class Answer extends SpeechType implements ANcontainers {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "answer";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 6);
        super.write(writer);
        writer.writeEnd(ADDRESS, 6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}
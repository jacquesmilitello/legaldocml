package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANcontainers;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element question is a container of a single official question as proposed by an MP to a person holding an
 * official position.
 *
 * <pre>
 * 	 &lt;xsd:element name="question" type="speechType"/&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Question extends SpeechType implements ANcontainers {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "question";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }
}
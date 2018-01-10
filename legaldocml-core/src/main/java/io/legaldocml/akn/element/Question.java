package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANcontainers;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.QUESTION;

/**
 * The element question is a container of a single official question as proposed by an MP to a person holding an
 * official position.
 *
 * <pre>
 * 	 <xsd:element name="question" type="speechType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Question extends SpeechType implements ANcontainers {

    /**
     * Memory address.
     */
    private static final long ADDRESS_QUESTION = Buffers.address(QUESTION);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_QUESTION, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS_QUESTION, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return QUESTION;
    }

}
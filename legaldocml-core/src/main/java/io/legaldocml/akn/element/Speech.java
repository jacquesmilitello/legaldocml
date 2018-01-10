package io.legaldocml.akn.element;


import io.legaldocml.akn.group.ANcontainers;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.SPEECH;

/**
 * The element speech is a container of a single speech utterance in a debate. Dialogs between speakers need a speech
 * element each.
 * <pre>
 * 	 <xsd:element name="speech" type="speechType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Speech extends SpeechType implements ANcontainers {

    /**
     * Memory address.
     */
    private static final long ADDRESS_SPEECH = Buffers.address(SPEECH);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_SPEECH, 6);
        super.write(writer);
        writer.writeEnd(ADDRESS_SPEECH, 6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return SPEECH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            super.accept(visitor);
            visitor.visitLeave(this);
        }
    }
}
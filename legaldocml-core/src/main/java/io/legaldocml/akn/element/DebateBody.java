package io.legaldocml.akn.element;

import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.DEBATE_BODY;

/**
 * The type debateBodyType specifies a content model of the main hierarchy of a debate.
 *
 * <pre>
 * 	 <xsd:element name="debateBody" type="debateBodyType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DebateBody extends DebateBodyType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DEBATE_BODY = Buffers.address(DEBATE_BODY);


    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DEBATE_BODY, 10);
        super.write(writer);
        writer.writeEnd(ADDRESS_DEBATE_BODY, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DEBATE_BODY;
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

package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANinline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.OUTCOME;

/**
 * The element outcome is an inline that wraps the outcome of a vote.
 *
 * <pre>
 *   <xsd:element name="outcome" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Outcome extends InlineType implements ANinline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_OUTCOME = Buffers.address(OUTCOME);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_OUTCOME, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS_OUTCOME, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return OUTCOME;
    }

}
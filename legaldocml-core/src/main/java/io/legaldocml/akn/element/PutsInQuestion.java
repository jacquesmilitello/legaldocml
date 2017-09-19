package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.PUTS_IN_QUESTION;

/**
 * The element putsInQuestions is a metadata element specifying a reference to a source questioned by the argument being
 * described.
 *
 * <pre>
 * 	 <xsd:element name="putsInQuestion" type="judicialArgumentType">
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class PutsInQuestion extends JudicialArgumentType implements JudicialArgumentsElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_PUTS_IN_QUESTION = Buffers.address(PUTS_IN_QUESTION);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_PUTS_IN_QUESTION, 14);
        super.write(writer);
        writer.writeEnd(ADDRESS_PUTS_IN_QUESTION, 14);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return PUTS_IN_QUESTION;
    }

}
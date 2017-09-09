package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.DEROGATES;

/**
 * The element derogates is a metadata element specifying a reference to a source derogated by the argument being
 * described.
 *
 * <pre>
 * 	 <xsd:element name="derogates" type="judicialArgumentType">
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Derogates extends JudicialArgumentType implements JudicialArgumentsElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DEROGATES = Buffers.address(DEROGATES);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DEROGATES, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS_DEROGATES, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DEROGATES;
    }

}
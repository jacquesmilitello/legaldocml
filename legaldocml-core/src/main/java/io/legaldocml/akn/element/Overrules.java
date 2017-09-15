package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.OVER_RULES;

/**
 * The element overrules is a metadata element specifying a reference to a source overruled by the argument being
 * described.
 *
 * <pre>
 * 	 <xsd:element name="overrules" type="judicialArgumentType">
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Overrules extends JudicialArgumentType implements JudicialArgumentsElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_OVER_RULES = Buffers.address(OVER_RULES);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_OVER_RULES, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS_OVER_RULES, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return OVER_RULES;
    }

}
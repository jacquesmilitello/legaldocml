package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANhier;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.RULE;

/**
 * This element is a hierarchical container called "rule" either explicitly or due to the local tradition.
 *
 * <pre>
 *   <xsd:element name="rule" type="hierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Rule extends Hierarchy implements ANhier {


    /**
     * Memory address.
     */
    private static final long ADDRESS_RULE = Buffers.address(RULE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_RULE, 4);
        super.write(writer);
        writer.writeEnd(ADDRESS_RULE, 4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return RULE;
    }
}
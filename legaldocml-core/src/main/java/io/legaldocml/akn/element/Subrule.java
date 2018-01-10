package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANhier;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.SUB_RULE;

/**
 * This element is a hierarchical container called "subrule" either explicitly or due to the local tradition.
 *
 * <pre>
 *   <xsd:element name="subrule" type="hierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Subrule extends Hierarchy implements ANhier {

    /**
     * Memory address.
     */
    private static final long ADDRESS_SUB_RULE = Buffers.address(SUB_RULE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_SUB_RULE, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS_SUB_RULE, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return SUB_RULE;
    }
}
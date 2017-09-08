package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANhier;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.CLAUSE;

/**
 * This element is a hierarchical container called "clause" either explicitly or due to the local tradition.
 *
 * <pre>
 *   <xsd:element name="clause" type="hierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Clause extends Hierarchy implements ANhier {

    /**
     * Memory address.
     */
    private static final long ADDRESS_CLAUSE = Buffers.address(CLAUSE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_CLAUSE, 6);
        super.write(writer);
        writer.writeEnd(ADDRESS_CLAUSE, 6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return CLAUSE;
    }

}
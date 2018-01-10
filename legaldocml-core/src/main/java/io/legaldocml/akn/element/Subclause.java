package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANhier;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.SUB_CLAUSE;

/**
 * This element is a hierarchical container called "subclause" either explicitly or due to the local tradition.
 *
 * <pre>
 * 	 <xsd:element name="subclause" type="hierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Subclause extends Hierarchy implements ANhier {

    /**
     * Memory address.
     */
    private static final long ADDRESS_SUB_CLAUSE = Buffers.address(SUB_CLAUSE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_SUB_CLAUSE, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS_SUB_CLAUSE, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return SUB_CLAUSE;
    }

}
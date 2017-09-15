package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANcontainers;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.NARRATIVE;

/**
 * The element narrative is a block element in a debate to mark description in the third person of events taking place
 * in the meeting, e.g. "Mr X. takes the Chair".
 *
 * <pre>
 * 	 <xsd:element name="narrative" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Narrative extends InlineType implements ANcontainers {

    /**
     * Memory address.
     */
    private static final long ADDRESS_NARRATIVE = Buffers.address(NARRATIVE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_NARRATIVE, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS_NARRATIVE, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return NARRATIVE;
    }


}
package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANsemanticInline;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.CONCEPT;

/**
 * The element concept is is an inline element to identify a text fragment introducing or referring to a concept in the
 * ontology.
 *
 * <pre>
 * 	 <xsd:element name="concept" type="inlinereqreq">
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Concept extends InlineReqReqType implements ANsemanticInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_CONCEPT = Buffers.address(CONCEPT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_CONCEPT, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS_CONCEPT, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return CONCEPT;
    }

}
package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANsemanticInline;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.EVENT;

/**
 * The element event is an inline element to identify a text fragment introducing or referring to an event in the
 * ontology.
 *
 * <pre>
 * 	 <xsd:element name="event" type="inlinereqreq">
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Event extends InlineReqReqType implements ANsemanticInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_EVENT = Buffers.address(EVENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_EVENT, 5);
        super.write(writer);
        writer.writeEnd(ADDRESS_EVENT, 5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return EVENT;
    }

}
package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANsemanticInline;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.LOCATION;

/**
 * The element location is an inline element to identify a text fragment introducing or referring to a location in the
 * ontology.
 *
 * <pre>
 * 	 <xsd:element name="location" type="inlinereqreq"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Location extends InlineReqReqType implements ANsemanticInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_LOCATION = Buffers.address(LOCATION);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_LOCATION, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS_LOCATION, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return LOCATION;
    }

}
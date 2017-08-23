package io.legaldocml.akn.element;

import io.legaldocml.akn.group.HTMLMarker;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element br is an HTML element and is used in Akoma Ntoso as in HTML, for the breaking of a line.
 *
 * <pre>
 *   <xsd:element name="br" type="markeropt"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Br extends MarkerOpt implements HTMLMarker {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "br";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 2);
        super.write(writer);
        writer.writeEnd(ADDRESS, 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}
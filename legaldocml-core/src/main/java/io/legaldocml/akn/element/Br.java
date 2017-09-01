package io.legaldocml.akn.element;

import io.legaldocml.akn.group.HTMLMarker;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.BR;

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
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(AknElementsAddress.BR, 2);
        super.write(writer);
        writer.writeEnd(AknElementsAddress.BR, 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return BR;
    }

}
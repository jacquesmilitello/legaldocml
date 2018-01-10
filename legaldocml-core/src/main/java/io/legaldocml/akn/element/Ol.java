package io.legaldocml.akn.element;

import io.legaldocml.akn.group.HTMLblock;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.OL;

/**
 * The element ol is an HTML element and is used in Akoma Ntoso as in HTML, for an ordered list of list item (elements
 * li).
 *
 * <pre>
 * 	 <xsd:element name="ol" type="listItems"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Ol extends ListItems implements LiElement, HTMLblock {

    /**
     * Memory address.
     */
    private static final long ADDRESS_OL = Buffers.address(OL);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_OL, 2);
        super.write(writer);
        writer.writeEnd(ADDRESS_OL, 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return OL;
    }

}
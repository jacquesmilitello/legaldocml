package io.legaldocml.akn.element;

import io.legaldocml.akn.group.HTMLblock;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

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
     * XML tag element name.
     */
    public static final String ELEMENT = "ol";

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
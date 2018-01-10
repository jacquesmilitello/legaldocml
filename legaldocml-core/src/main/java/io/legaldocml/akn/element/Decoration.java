package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANinline;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.DECORATION;

/**
 * the element decoration is an inline element to represent a decorative aspect that is present in the orignal text and
 * that is meant as additional information to the text (e.g., the annotation 'new' on the side of a freshly inserted
 * structure.
 *
 * <pre>
 * 	 <xsd:element name="decoration" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Decoration extends InlineType implements ANinline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DECORATION = Buffers.address(DECORATION);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DECORATION, 10);
        super.write(writer);
        writer.writeEnd(ADDRESS_DECORATION, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DECORATION;
    }

}
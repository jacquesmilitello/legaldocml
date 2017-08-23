package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANinline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

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
     * XML tag element name.
     */
    public static final String ELEMENT = "decoration";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 10);
        super.write(writer);
        writer.writeEnd(ADDRESS, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}
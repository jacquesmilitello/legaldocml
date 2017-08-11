package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANsemanticInline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * the element def is an inline element used for the definition of a term used in the rest of the document.
 * <p/>
 * <pre>
 * 	 <xsd:element name="def" type="inline"/>
 * </pre>
 */
public final class Def extends InlineType implements ANsemanticInline {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "def";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 3);
        super.write(writer);
        writer.writeEnd(ADDRESS, 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}
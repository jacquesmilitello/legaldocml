package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANmarker;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element eol (end of line) is a marker for where in the original text the line breaks. If the line breaks within a
 * word, place the element BEFORE the word and place the number of characters before the break in the attribute breakAt.
 * One can also specify, if relevant, the hyphen or other character used to signal the break of a word at the end of the
 * line with the attribute breakWith.
 *
 * <pre>
 * 	 &lt;xsd:element name="eol" type="eolType"/&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Eol extends EolType implements ANmarker {

    /**
     * XML Element Name.
     */
    public static final String ELEMENT = "eol";

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

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            super.accept(visitor);
            visitor.visitLeave(this);
        }
    }
}
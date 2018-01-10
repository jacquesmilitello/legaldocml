package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANmarker;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.EOP;

/**
 * The element eop (end of page) is a marker for where in the original text the page breaks. Do NOT use a eol element,
 * too. If the page breaks within a word, place the element BEFORE the word and place the number of characters before
 * the break in the attribute breakAt. One can also specify, if relevant, the hyphen or other character used to signal
 * the break of a word at the end of the page with the attribute breakWith.
 *
 * <pre>
 * 	<xsd:element name="eop" type="eolType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Eop extends EolType implements ANmarker {

    /**
     * Memory address.
     */
    private static final long ADDRESS_EOP = Buffers.address(EOP);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_EOP, 3);
        super.write(writer);
        writer.writeEnd(ADDRESS_EOP, 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return EOP;
    }

}
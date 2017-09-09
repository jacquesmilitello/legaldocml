package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANsemanticInline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.DEF;

/**
 * the element def is an inline element used for the definition of a term used in the rest of the document.
 *
 * <pre>
 * 	 <xsd:element name="def" type="inline"/>
 * </pre>
 */
public final class Def extends InlineType implements ANsemanticInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DEF = Buffers.address(DEF);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DEF, 3);
        super.write(writer);
        writer.writeEnd(ADDRESS_DEF, 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DEF;
    }

}
package io.legaldocml.akn.element;

import io.legaldocml.akn.group.HTMLinline;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.SUB;

/**
 * The element sub is an HTML element and is used in Akoma Ntoso as in HTML, for the subscript style (an inline).
 *
 * <pre>
 *   <xsd:element name="sub" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Sub extends InlineType implements HTMLinline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_SUB = Buffers.address(SUB);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_SUB, 3);
        super.write(writer);
        writer.writeEnd(ADDRESS_SUB, 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return SUB;
    }

}
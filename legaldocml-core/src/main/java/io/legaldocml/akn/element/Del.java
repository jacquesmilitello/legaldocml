package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANinline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.DEL;

/**
 * The element del is an inline element for the specification of editorial deletions.
 *
 * <pre>
 *   <xsd:element name="del" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Del extends InlineType implements ANinline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DEL = Buffers.address(DEL);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DEL, 3);
        super.write(writer);
        writer.writeEnd(ADDRESS_DEL, 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DEL;
    }

}
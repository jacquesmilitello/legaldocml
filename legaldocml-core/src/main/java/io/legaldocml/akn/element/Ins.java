package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANinline;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.INS;

/**
 * The element ins is an inline element for the specification of editorial insertions.
 *
 * <pre>
 *   <xsd:element name="ins" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Ins extends InlineType implements ANinline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_INS = Buffers.address(INS);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_INS, 3);
        super.write(writer);
        writer.writeEnd(ADDRESS_INS, 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return INS;
    }

}
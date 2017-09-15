package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANinline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.OMISSIS;

/**
 * the element omissis is an inline element for the specification of a text that substitutes a textual omission (e.g.,
 * dots, spaces, the word "omissis", etc.
 *
 * <pre>
 *   <xsd:element name="omissis" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Omissis extends InlineType implements ANinline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_OMISSIS = Buffers.address(OMISSIS);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_OMISSIS, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS_OMISSIS, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return OMISSIS;
    }

}
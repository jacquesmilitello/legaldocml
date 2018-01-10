package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANheaderInline;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.SIGNATURE;

/**
 * The element signature is an inline element within conclusions to identify where the document defines one of the
 * signatures.
 *
 * <pre>
 * 	 <xsd:element name="signature" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Signature extends InlineType implements ANheaderInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_SIGNATURE = Buffers.address(SIGNATURE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_SIGNATURE, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS_SIGNATURE, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return SIGNATURE;
    }

}
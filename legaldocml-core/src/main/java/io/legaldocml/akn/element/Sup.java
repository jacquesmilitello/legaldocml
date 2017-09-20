package io.legaldocml.akn.element;

import io.legaldocml.akn.group.HTMLinline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.SUP;

/**
 * The element sup is an HTML element and is used in Akoma Ntoso as in HTML, for the superscript style (an inline).
 *
 * <pre>
 * 	 <xsd:element name="sup" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Sup extends InlineType implements HTMLinline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_SUP = Buffers.address(SUP);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_SUP, 3);
        super.write(writer);
        writer.writeEnd(ADDRESS_SUP, 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return SUP;
    }


}
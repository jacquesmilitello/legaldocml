package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element passiveModifications is a metadata container of the passive modifications affecting the document.
 * <p/>
 * <pre>
 * 	 <xsd:element name="passiveModifications" type="Amendments"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class PassiveModifications extends Amendments {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "passiveModifications";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 20);
        super.write(writer);
        writer.writeEnd(ADDRESS, 20);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}

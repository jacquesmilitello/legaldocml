package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.PASSIVE_MODIFICATIONS;

/**
 * The element passiveModifications is a metadata container of the passive modifications affecting the document.
 *
 * <pre>
 * 	 <xsd:element name="passiveModifications" type="Amendments"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class PassiveModifications extends Amendments {

    /**
     * Memory address.
     */
    private static final long ADDRESS_PASSIVE_MODIFICATIONS = Buffers.address(PASSIVE_MODIFICATIONS);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_PASSIVE_MODIFICATIONS, 20);
        super.write(writer);
        writer.writeEnd(ADDRESS_PASSIVE_MODIFICATIONS, 20);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return PASSIVE_MODIFICATIONS;
    }

}

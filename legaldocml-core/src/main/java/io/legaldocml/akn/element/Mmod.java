package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANinline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.MMOD;

/**
 * the element mmod is an inline element containing multiple specifications of modifications on another document.
 *
 * <pre>
 *   <xsd:element name="mmod" type="modType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Mmod extends ModType implements ANinline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_MMOD = Buffers.address(MMOD);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_MMOD, 4);
        super.write(writer);
        writer.writeEnd(ADDRESS_MMOD, 4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return MMOD;
    }

}
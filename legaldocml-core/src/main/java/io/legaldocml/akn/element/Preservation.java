package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element preservation is the metadata property containing an arbitrary list of elements detailing the preservation
 * actions taken for the document is the respective level of the FRBR hierarchy.
 *
 * <pre>
 * 	 &lt;xsd:element name="preservation" type="anyOtherType"/&gt;
 * <pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Preservation extends AnyOtherType {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "preservation";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 12);
        super.write(writer);
        writer.writeEnd(ADDRESS, 12);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}
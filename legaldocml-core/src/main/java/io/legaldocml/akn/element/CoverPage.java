package io.legaldocml.akn.element;

import io.legaldocml.akn.AknObject;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element coverPage is used as a container of the text that acts as a cover page.
 * <p/>
 * <pre>
 * 	 <xsd:element name="coverPage" type="basicopt"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class CoverPage extends Basicopt implements AknObject, PortionBodyTypeElement {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "coverPage";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}
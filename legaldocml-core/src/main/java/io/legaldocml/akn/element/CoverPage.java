package io.legaldocml.akn.element;

import io.legaldocml.akn.AknObject;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.COVER_PAGE;

/**
 * The element coverPage is used as a container of the text that acts as a cover page.
 *
 * <pre>
 * 	 <xsd:element name="coverPage" type="basicopt"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class CoverPage extends Basicopt implements AknObject, PortionBodyTypeElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_COVER_PAGE = Buffers.address(COVER_PAGE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_COVER_PAGE, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS_COVER_PAGE, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return COVER_PAGE;
    }

}
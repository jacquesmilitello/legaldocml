package io.legaldocml.akn.element;

import io.legaldocml.akn.group.BasicContainers;
import io.legaldocml.akn.group.PrefaceContainers;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.LONG_TITLE;

/**
 * The element longTitle is the section of the preface or preamble that is called long title.
 *
 * <pre>
 *   <xsd:element name="longTitle" type="blocksreq">
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class LongTitle extends Blocksreq implements PrefaceContainers, PopupStructureElement, SubFlowStructureElement, PortionBodyTypeElement, BasicContainers {

    /**
     * Memory address.
     */
    private static final long ADDRESS_LONG_TITLE = Buffers.address(LONG_TITLE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_LONG_TITLE, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS_LONG_TITLE, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return LONG_TITLE;
    }

}
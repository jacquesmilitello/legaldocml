package io.legaldocml.akn.element;

import io.legaldocml.akn.group.BasicContainers;
import io.legaldocml.akn.group.PrefaceContainers;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element longTitle is the section of the preface or preamble that is called long title.
 *
 * <pre>
 *   &lt;xsd:element name="longTitle" type="blocksreq"&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class LongTitle extends Blocksreq implements PrefaceContainers, PopupStructureElement, SubFlowStructureElement, PortionBodyTypeElement, BasicContainers {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "longTitle";

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
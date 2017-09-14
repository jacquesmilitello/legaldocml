package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.INTRO;

/**
 * the element intro is a heading element in a hierarchy that contains paragraphs introducing one or more lower
 * hierarchical elements.
 *
 * <pre>
 * 	 <xsd:element name="intro" type="blocksreq"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Intro extends Blocksreq implements PopupStructureElement, SubFlowStructureElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_INTRO = Buffers.address(INTRO);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_INTRO, 5);
        super.write(writer);
        writer.writeEnd(ADDRESS_INTRO, 5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return INTRO;
    }
}
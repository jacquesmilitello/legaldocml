package io.legaldocml.akn.element;

import io.legaldocml.akn.group.SpeechSection;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.PAPERS;

/**
 * This element is a structural container for parts of a debates that are relevant to the display of papers.
 *
 * <pre>
 *  <xsd:element name="papers" type="althierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Papers extends AltHierarchy implements SpeechSection {

    /**
     * Memory address.
     */
    private static final long ADDRESS_PAPERS = Buffers.address(PAPERS);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_PAPERS, 6);
        super.write(writer);
        writer.writeEnd(ADDRESS_PAPERS, 6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return PAPERS;
    }

}
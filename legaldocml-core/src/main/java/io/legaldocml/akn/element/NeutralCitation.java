package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANheaderInline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.NEUTRAL_CITATION;

/**
 * The element neutralCitation is an inline element within judgments to identify the string declared by the document as
 * being the neutral citation for the judgment.
 *
 * <pre>
 * 	 <xsd:element name="neutralCitation" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class NeutralCitation extends InlineType implements ANheaderInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_NEUTRAL_CITATION = Buffers.address(NEUTRAL_CITATION);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_NEUTRAL_CITATION, 15);
        super.write(writer);
        writer.writeEnd(ADDRESS_NEUTRAL_CITATION, 15);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return NEUTRAL_CITATION;
    }

}
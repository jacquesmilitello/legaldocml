package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANheaderInline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element neutralCitation is an inline element within judgments to identify the string declared by the document as
 * being the neutral citation for the judgment.
 * <p/>
 * <pre>
 * 	 <xsd:element name="neutralCitation" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class NeutralCitation extends InlineType implements ANheaderInline {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "neutralCitation";

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
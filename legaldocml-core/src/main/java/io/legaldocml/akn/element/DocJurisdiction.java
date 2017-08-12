package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANtitleInline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element docJurisdiction is an inline element within preface to identify the string used by the document detailing
 * the jurisdiction of the document.
 *
 * <pre>
 * 	 &lt;xsd:element name="docJurisdiction" type="inline"/&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DocJurisdiction extends InlineType implements ANtitleInline {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "docJurisdiction";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 15);
        super.write(writer);
        writer.writeEnd(ADDRESS, 15);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}
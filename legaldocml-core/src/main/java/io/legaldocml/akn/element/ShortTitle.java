package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANtitleInline;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.SHORT_TITLE;

/**
 * The element shortTitle is an inline element within preface to identify the string used by the document for the short
 * title of the document.
 *
 * <pre>
 * 	 <xsd:element name="shortTitle" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ShortTitle extends InlineType implements ANtitleInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(SHORT_TITLE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 10);
        super.write(writer);
        writer.writeEnd(ADDRESS, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return SHORT_TITLE;
    }

}
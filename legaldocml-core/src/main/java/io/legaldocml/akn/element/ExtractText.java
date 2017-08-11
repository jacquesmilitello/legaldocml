package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANinline;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

/**
 * The element extractText is an inline element containing a string used as an extract from another document.
 * <p/>
 * <pre>
 * 	<xsd:element name="extractText" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@Deprecated
public final class ExtractText extends InlineType implements ANinline {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "extractText";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 11);
        super.write(writer);
        writer.writeEnd(ADDRESS, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}
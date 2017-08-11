package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANtitleInline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element docIntroducer is an inline element within preface to identify the string used by the document detailing
 * the individual introducing of the document.
 * <p/>
 * <pre>
 * 	 <xsd:element name="docIntroducer" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DocIntroducer extends InlineType implements ANtitleInline {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "docIntroducer";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 13);
        super.write(writer);
        writer.writeEnd(ADDRESS, 13);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}
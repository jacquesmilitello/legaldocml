package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANtitleInline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.DOC_INTRODUCER;

/**
 * The element docIntroducer is an inline element within preface to identify the string used by the document detailing
 * the individual introducing of the document.
 *
 * <pre>
 * 	 <xsd:element name="docIntroducer" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DocIntroducer extends InlineType implements ANtitleInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DOC_INTRODUCER = Buffers.address(DOC_INTRODUCER);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DOC_INTRODUCER, 13);
        super.write(writer);
        writer.writeEnd(ADDRESS_DOC_INTRODUCER, 13);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DOC_INTRODUCER;
    }

}
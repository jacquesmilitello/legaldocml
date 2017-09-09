package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANtitleInline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.DOC_PURPOSE;

/**
 * The element docPurpose is an inline element within preface to identify the string used by the document detailing its
 * own purpose
 *
 * <pre>
 * 	 <xsd:element name="docPurpose" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DocPurpose extends InlineType implements ANtitleInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DOC_PURPOSE = Buffers.address(DOC_PURPOSE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DOC_PURPOSE, 10);
        super.write(writer);
        writer.writeEnd(ADDRESS_DOC_PURPOSE, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DOC_PURPOSE;
    }

}
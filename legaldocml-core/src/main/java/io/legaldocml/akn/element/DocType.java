package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANtitleInline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.DOC_TYPE;

/**
 * The element docType is an inline element within preface to identify the string used by the document for its own type.
 *
 * <pre>
 * 	 <xsd:element name="docType" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DocType extends InlineType implements ANtitleInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DOC_TYPE = Buffers.address(DOC_TYPE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DOC_TYPE, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS_DOC_TYPE, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DOC_TYPE;
    }

}
package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANtitleInline;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.DOC_STAGE;

/**
 * the element docStage is an inline element within preface to identify the string used by the document detailing the
 * stage in which the document sits.
 *
 * <pre>
 *   <xsd:element name="docStage" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DocStage extends InlineType implements ANtitleInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DOC_STAGE = Buffers.address(DOC_STAGE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DOC_STAGE, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS_DOC_STAGE, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DOC_STAGE;
    }

}
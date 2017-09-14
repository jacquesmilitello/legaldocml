package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANtitleInline;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.DOC_TITLE;

/**
 * the element docTitle is an inline element within preface to identify the string used by the document for its own
 * title.
 *
 * <pre>
 * 	 <xsd:element name="docTitle" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DocTitle extends InlineType implements ANtitleInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DOC_TITLE = Buffers.address(DOC_TITLE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DOC_TITLE, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS_DOC_TITLE, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DOC_TITLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            super.accept(visitor);
            visitor.visitLeave(this);
        }
    }
}
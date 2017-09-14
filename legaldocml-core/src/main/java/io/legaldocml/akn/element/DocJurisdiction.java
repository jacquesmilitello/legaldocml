package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANtitleInline;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.DOC_JURISDICTION;

/**
 * The element docJurisdiction is an inline element within preface to identify the string used by the document detailing
 * the jurisdiction of the document.
 *
 * <pre>
 * 	 <xsd:element name="docJurisdiction" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DocJurisdiction extends InlineType implements ANtitleInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DOC_JURISDICTION = Buffers.address(DOC_JURISDICTION);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DOC_JURISDICTION, 15);
        super.write(writer);
        writer.writeEnd(ADDRESS_DOC_JURISDICTION, 15);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DOC_JURISDICTION;
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
package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANsemanticInline;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.TERM;

/**
 * The element term is an inline element to identify a text fragment introducing or referring to a term in the ontology
 *
 * <pre>
 * 	 <xsd:element name="term" type="inlinereqreq"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Term extends InlineReqReqType implements ANsemanticInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_TERM = Buffers.address(TERM);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_TERM, 4);
        super.write(writer);
        writer.writeEnd(ADDRESS_TERM, 4);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return TERM;
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
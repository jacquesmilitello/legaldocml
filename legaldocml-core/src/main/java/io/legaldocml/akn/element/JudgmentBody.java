package io.legaldocml.akn.element;

import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.JUDGMENT_BODY;

/**
 * The element judgmentBody is the container of the main hierarchy of a judgment document.
 *
 * <pre>
 * 	 <xsd:element name="judgmentBody" type="judgmentBodyType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class JudgmentBody extends JudgmentBodyType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_JUDGMENT_BODY = Buffers.address(JUDGMENT_BODY);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_JUDGMENT_BODY, 12);
        super.write(writer);
        writer.writeEnd(ADDRESS_JUDGMENT_BODY, 12);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return JUDGMENT_BODY;
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

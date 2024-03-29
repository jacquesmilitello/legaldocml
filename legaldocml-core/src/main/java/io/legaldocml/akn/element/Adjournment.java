package io.legaldocml.akn.element;

import io.legaldocml.akn.group.SpeechSection;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.ADJOURNMENT;

/**
 * This element is a structural container for parts of a debates that contain adjournment notices.
 *
 * <pre>
 * 	 <xsd:element name="adjournment" type="althierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Adjournment extends AltHierarchy implements SpeechSection {

    /**
     * Memory address.
     */
    private static final long ADDRESS_ADJOURNMENT = Buffers.address(ADJOURNMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_ADJOURNMENT, 11);
        super.write(writer);
        writer.writeEnd(ADDRESS_ADJOURNMENT, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ADJOURNMENT;
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
package io.legaldocml.akn.element;

import io.legaldocml.akn.group.SpeechSection;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.ADMINISTATION_OF_OATH;

/**
 * This element is a structural container for parts of a debates that contain the administration of an oath.
 *
 * <pre>
 * 	 <xsd:element name="administrationOfOath" type="althierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AdministrationOfOath extends AltHierarchy implements SpeechSection {

    /**
     * Memory address.
     */
    private static final long ADDRESS_ADMINISTATION_OF_OATH = Buffers.address(ADMINISTATION_OF_OATH);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_ADMINISTATION_OF_OATH, 20);
        super.write(writer);
        writer.writeEnd(ADDRESS_ADMINISTATION_OF_OATH, 20);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ADMINISTATION_OF_OATH;
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
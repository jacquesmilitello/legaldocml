package io.legaldocml.akn.element;

import io.legaldocml.akn.group.SpeechSection;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

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
     * XML tag element name.
     */
    public static final String ELEMENT_ADMINISTATION_OF_OATH = "administrationOfOath";

    /**
     * Memory address.
     */
    private static final long ADDRESS_ADMINISTATION_OF_OATH = Buffers.address(ELEMENT_ADMINISTATION_OF_OATH);

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
        return ELEMENT_ADMINISTATION_OF_OATH;
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
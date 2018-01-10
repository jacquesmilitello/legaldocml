package io.legaldocml.akn.element;

import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.MAIN_BODY;

/**
 * The element mainContent is the container of the main part of all other document types.
 *
 * <pre>
 * 	 <xsd:element name="mainBody" type="maincontent"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class MainBody extends MainContent {

    /**
     * Memory address.
     */
    private static final long ADDRESS_MAIN_BODY = Buffers.address(MAIN_BODY);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_MAIN_BODY, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS_MAIN_BODY, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return MAIN_BODY;
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
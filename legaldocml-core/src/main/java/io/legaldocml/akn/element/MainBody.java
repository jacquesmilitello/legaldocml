package io.legaldocml.akn.element;

import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element mainContent is the container of the main part of all other document types.
 * <p/>
 * <pre>
 * 	 <xsd:element name="mainBody" type="maincontent"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class MainBody extends MainContent {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "mainBody";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);


    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
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
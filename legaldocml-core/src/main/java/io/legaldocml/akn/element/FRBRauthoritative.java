package io.legaldocml.akn.element;


import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

/**
 * The element FRBRauthoritative is the metadata property containing a boolean value to determine whether the document
 * contains authoritative text (i.e., content that is the official, authoritative product of an official workflow from
 * an entity that is entrusted with generating an official, authoriative version of the document.
 *
 * <pre>
 *   <xsd:element name="FRBRauthoritative" type="booleanValueType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRauthoritative extends BooleanValueType {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "FRBRauthoritative";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 17);
        super.write(writer);
        writer.writeEnd(ADDRESS, 17);
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
        visitor.visit(this);
    }
}
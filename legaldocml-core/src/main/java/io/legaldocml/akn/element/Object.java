package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANsemanticInline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element object is is an inline element to identify a text fragment introducing or referring to an object in the
 * ontology.
 * <p/>
 * <pre>
 *   <xsd:element name="object" type="inlinereqreq"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Object extends InlineReqReqType implements ANsemanticInline {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "object";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 6);
        super.write(writer);
        writer.writeEnd(ADDRESS, 6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}
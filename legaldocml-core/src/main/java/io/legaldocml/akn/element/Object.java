package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANsemanticInline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.OBJECT;

/**
 * The element object is is an inline element to identify a text fragment introducing or referring to an object in the
 * ontology.
 *
 * <pre>
 *   <xsd:element name="object" type="inlinereqreq"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Object extends InlineReqReqType implements ANsemanticInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_OBJECT = Buffers.address(OBJECT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_OBJECT, 6);
        super.write(writer);
        writer.writeEnd(ADDRESS_OBJECT, 6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return OBJECT;
    }

}
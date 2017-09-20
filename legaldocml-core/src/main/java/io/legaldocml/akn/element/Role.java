package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANsemanticInline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.ROLE;

/**
 * The element role is an inline element to identify a text fragment introducing or referring to a role in the ontology.
 *
 * <pre>
 *   <xsd:element name="role" type="inlinereqreq"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Role extends InlineReqReqType implements ANsemanticInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_ROLE = Buffers.address(ROLE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_ROLE, 4);
        super.write(writer);
        writer.writeEnd(ADDRESS_ROLE, 4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ROLE;
    }

}
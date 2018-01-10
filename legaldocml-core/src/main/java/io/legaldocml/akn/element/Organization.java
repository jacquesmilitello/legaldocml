package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANsemanticInline;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.ORGANIZATION;

/**
 * The element organization is an inline element to identify a text fragment introducing or referring to an organization
 * in the ontology.
 *
 * <pre>
 * 	 <xsd:element name="organization" type="inlinereqreq"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Organization extends InlineReqReqType implements ANsemanticInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_ORGANIZATION = Buffers.address(ORGANIZATION);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_ORGANIZATION, 12);
        super.write(writer);
        writer.writeEnd(ADDRESS_ORGANIZATION, 12);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ORGANIZATION;
    }

}
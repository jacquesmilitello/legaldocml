package io.legaldocml.akn.element;

import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.DOMAIN;

/**
 * The element domain is a metadata element containing (in some non-managed form) the domain to which the modification
 * applies.
 *
 * <pre>
 *   <xsd:element name="domain" type="anyOtherType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Domain extends AnyOtherType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DOMAIN = Buffers.address(DOMAIN);


    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DOMAIN, 6);
        super.write(writer);
        writer.writeEnd(ADDRESS_DOMAIN, 6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DOMAIN;
    }

}
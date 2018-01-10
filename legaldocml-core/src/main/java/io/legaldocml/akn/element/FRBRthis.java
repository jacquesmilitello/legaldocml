package io.legaldocml.akn.element;


import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;
import io.legaldocml.util.Strings;

import java.io.IOException;
import java.util.Objects;

import static io.legaldocml.akn.AknElements.FRBR_THIS;

/**
 * The element FRBRthis is the metadata property containing the IRI of the specific component of the document.
 *
 * <pre>
 *   <xsd:element name="FRBRthis" type="valueType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRthis extends ValueType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_FRBR_THIS = Buffers.address(FRBR_THIS);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_FRBR_THIS, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS_FRBR_THIS, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return FRBR_THIS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(java.lang.Object obj) {
        return this == obj || obj != null && (obj instanceof FRBRthis) && Objects.equals(getValue(), ((FRBRthis) obj).getValue());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        if (Strings.isEmpty(getValue())) {
            return super.hashCode();
        }
        return getValue().hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        visitor.visit(this);
    }

}
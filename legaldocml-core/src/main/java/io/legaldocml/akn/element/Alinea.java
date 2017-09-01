package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANhier;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.ALINEA;

/**
 * This element is a hierarchical container called "alinea" either explicitly or due to the local tradition.
 *
 * <pre>
 *   <xsd:element name="alinea" type="hierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Alinea extends Hierarchy implements ANhier {

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(AknElementsAddress.ALINEA, 6);
        super.write(writer);
        writer.writeEnd(AknElementsAddress.ALINEA, 6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ALINEA;
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
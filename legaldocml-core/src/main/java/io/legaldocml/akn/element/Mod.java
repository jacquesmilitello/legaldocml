package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANinline;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.MOD;

/**
 * The element mod is an inline element containing the specification of a modification on another document.
 *
 * <pre>
 *   <xsd:element name="mod" type="modType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Mod extends ModType implements ANinline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_MOD = Buffers.address(MOD);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_MOD, 3);
        super.write(writer);
        writer.writeEnd(ADDRESS_MOD, 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return MOD;
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
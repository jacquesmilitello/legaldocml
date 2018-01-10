package io.legaldocml.akn.element;

import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.PREFACE;

/**
 * The element preface is used as a container of all prefacing material (e.g. headers, formulas, etc.).
 *
 * <pre>
 * 	 <xsd:element name="preface" type="prefaceopt"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Preface extends Prefaceopt implements PortionBodyTypeElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_PREFACE = Buffers.address(PREFACE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_PREFACE, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS_PREFACE, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return PREFACE;
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
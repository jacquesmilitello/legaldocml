package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.attribute.IdReq;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.AknElements.COMPONENT;
import static io.legaldocml.akn.AknElements.COMPONENTS;

/**
 * <pre>
 *   <xsd:element name="components">
 *     <xsd:complexType>
 *       <xsd:sequence>
 *         <xsd:element ref="component" minOccurs="1" maxOccurs="unbounded"/>
 *       <xsd:sequence>
 *       <xsd:attributeGroup ref="idreq"/>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Components extends AbstractId implements IdReq {

    /**
     * Memory address.
     */
    private static final long ADDRESS_COMPONENTS = Buffers.address(COMPONENTS);

    private static final ImmutableMap<String, Supplier<Component>> ELEMS;

    static {
        ELEMS = ImmutableMap.of(COMPONENT, Component::new);
    }

    // Mandatory (min 1).
    private final AknList<Component> elements = new AknList<>(new Component[4]);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_COMPONENTS, 10);
        IdReq.super.write(writer);
        this.elements.write(writer);
        writer.writeEnd(ADDRESS_COMPONENTS, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        XmlReaderHelper.read(reader, this.elements, ELEMS);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return COMPONENTS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            this.elements.accept(visitor);
            visitor.visitLeave(this);
        }
    }
}
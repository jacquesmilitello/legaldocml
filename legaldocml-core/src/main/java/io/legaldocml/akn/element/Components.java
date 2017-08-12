package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.function.Supplier;

/**
 * <pre>
 *   &lt;xsd:element name="components"&gt;
 *     &lt;xsd:complexType&gt;
 *       &lt;xsd:sequence&gt;
 *         &lt;xsd:element ref="component" minOccurs="1" maxOccurs="unbounded"/&gt;
 *       &lt;xsd:sequence&gt;
 *       &lt;xsd:attributeGroup ref="idreq"/&gt;
 * 	   &lt;xsd:complexType&gt;
 *   &lt;xsd:element&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Components extends IdReqImpl {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "components";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, Supplier<Component>> ELEMS;

    static {
        ELEMS = ImmutableMap.of(Component.ELEMENT, Component::new);
    }

    // Mandatory (min 1).
    private final AknList<Component> elements = new AknList<>(new Component[4]);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 10);
        super.write(writer);
        this.elements.write(writer);
        writer.writeEnd(ADDRESS, 10);
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
        return ELEMENT;
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
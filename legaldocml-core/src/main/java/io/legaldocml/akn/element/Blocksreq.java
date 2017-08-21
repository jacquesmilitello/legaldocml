package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.group.BlockElements;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.element.Groups.blockElements;
import static io.legaldocml.akn.element.Groups.convertSuper;

/**
 * the complex type blocksreq defines the content model and attributes shared by all containers. Here the eId attribute
 * is required.
 * <p>
 * <pre>
 *   &lt;xsd:complexType name="blocksreq"&gt;
 *     &lt;xsd:sequence minOccurs="0" maxOccurs="unbounded"&gt;
 *       &lt;xsd:group ref="blockElements" /&gt;
 * 	   &lt;xsd:sequence&gt;
 * 	   &lt;xsd:attributeGroup ref="corereq" /&gt;
 *   &lt;xsd:complexType&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class Blocksreq extends CoreReqImpl {

    private static final ImmutableMap<String, Supplier<BlockElements>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<BlockElements>>builder()
                .putAll(convertSuper(blockElements()))
                .build();
    }

    // Mandatory
    private final AknList<BlockElements> elements = new AknList<>(new BlockElements[8]);

    public final void add(BlockElements el) {
        this.elements.add(el);
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
    public void write(XmlWriter writer) throws IOException {
        super.write(writer);
        this.elements.write(writer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        this.elements.accept(visitor);
    }
}
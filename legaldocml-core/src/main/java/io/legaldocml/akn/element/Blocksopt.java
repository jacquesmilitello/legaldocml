package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.group.BlockElements;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.visitor.group.BlockElementsVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.element.Groups.blockElements;
import static io.legaldocml.akn.element.Groups.convertSuper;

/**
 * The complex type blocksopt defines the content model and attributes shared by all containers. Here the eId attribute
 * is optional.
 *
 * <pre>
 *   &lt;xsd:complexType name="blocksopt"&gt;
 * 	   &lt;xsd:sequence minOccurs="0" maxOccurs="unbounded"&gt;
 * 	     &lt;xsd:group ref="blockElements"/&gt;
 * 	   &lt;xsd:sequence&gt;
 * 	   &lt;xsd:attributeGroup ref="coreopt"/&gt;
 *   &lt;xsd:complexType&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class Blocksopt<T extends BlockElementsVisitor> extends CoreOptImpl {

    // Mandatory (min 1).
    private final AknList<BlockElements> elements = new AknList<BlockElements>(new BlockElements[4]);

    private static final ImmutableMap<String, Supplier<BlockElements>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<BlockElements>>builder()
                .putAll(convertSuper(blockElements()))
                .build();
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

}
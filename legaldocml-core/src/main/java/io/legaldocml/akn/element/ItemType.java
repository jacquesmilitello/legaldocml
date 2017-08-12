package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.group.BlockElements;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.element.Groups.blockElements;
import static io.legaldocml.akn.element.Groups.convertSuper;

/**
 * The complex type itemType is similar to a hierarchical element, but is isolated and does not belong to a full
 * hierarchy.
 *
 * <pre>
 *   &lt;xsd:complexType name="itemType"&gt;
 * 	   &lt;xsd:complexContent&gt;
 * 	     &lt;xsd:extension base="basehierarchy"&gt;
 * 		   &lt;xsd:sequence minOccurs="1" maxOccurs="unbounded"&gt;
 * 		     &lt;xsd:group ref="blockElements" /&gt;
 * 		   &lt;xsd:sequence&gt;
 * 		   &lt;xsd:attributeGroup ref="corereq" /&gt;
 * 	     &lt;xsd:extension&gt;
 * 	   &lt;xsd:complexContent&gt;
 *   &lt;xsd:complexType&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class ItemType extends BaseHierarchyCoreReq {

    // Mandatory (min 1)
    private final AknList<BlockElements> blockElements = new AknList<>(new BlockElements[4]);

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
        QName qName = reader.getQName();
        super.read(reader);
        XmlReaderHelper.read(reader, this.blockElements, ELEMS, qName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        super.write(writer);
        this.blockElements.write(writer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        super.accept(visitor);
        this.blockElements.accept(visitor);
    }

}
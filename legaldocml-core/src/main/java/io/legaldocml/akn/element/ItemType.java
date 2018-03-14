package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.attribute.CoreReq;
import io.legaldocml.akn.container.BlockElementsContainer;
import io.legaldocml.akn.group.ANblock;
import io.legaldocml.akn.group.BlockElements;
import io.legaldocml.akn.group.HTMLblock;
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
import static java.util.Objects.requireNonNull;

/**
 * The complex type itemType is similar to a hierarchical element, but is isolated and does not belong to a full
 * hierarchy.
 *
 * <pre>
 *   <xsd:complexType name="itemType">
 * 	   <xsd:complexContent>
 * 	     <xsd:extension base="basehierarchy">
 * 		   <xsd:sequence minOccurs="1" maxOccurs="unbounded">
 * 		     <xsd:group ref="blockElements" />
 * 		   <xsd:sequence>
 * 		   <xsd:attributeGroup ref="corereq" />
 * 	     </xsd:extension>
 * 	   </xsd:complexContent>
 *   </xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class ItemType extends BaseHierarchy implements CoreReq, BlockElementsContainer<BlockElements> {

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
    public void add(BlockElements elements) {
        this.blockElements.add(requireNonNull(elements));
    }

    public final boolean remove(BlockElements elements) {
        return this.blockElements.remove(requireNonNull(elements));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(ANblock block) {
        this.blockElements.add(requireNonNull(block));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(ANblock block) {
        return remove((BlockElements)block);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(HTMLblock block) {
        this.blockElements.add(requireNonNull(block));
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
        CoreReq.super.write(writer);
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
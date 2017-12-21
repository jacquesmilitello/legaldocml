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
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.element.Groups.blockElements;
import static io.legaldocml.akn.element.Groups.convertSuper;
import static java.util.Objects.requireNonNull;

/**
 * the complex type blocksreq defines the content model and attributes shared by all containers. Here the eId attribute
 * is required.
 * <p>
 * <pre>
 *   <xsd:complexType name="blocksreq">
 *     <xsd:sequence minOccurs="0" maxOccurs="unbounded">
 *       <xsd:group ref="blockElements" />
 * 	   <xsd:sequence>
 * 	   <xsd:attributeGroup ref="corereq" />
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class Blocksreq extends AbstractCore implements CoreReq, BlockElementsContainer {

    private static final ImmutableMap<String, Supplier<BlockElements>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<BlockElements>>builder()
                .putAll(convertSuper(blockElements()))
                .build();
    }

    // Mandatory
    private final AknList<BlockElements> elements = new AknList<>(new BlockElements[8]);

    public boolean removeBlockElements(BlockElements element) {
        return this.elements.remove(requireNonNull(element));
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public final void add(BlockElements el) {
        this.elements.add(requireNonNull(el));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(HTMLblock block) {
        this.elements.add(requireNonNull(block));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(ANblock block) {
        this.elements.add(requireNonNull(block));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(ANblock block) {
        return removeBlockElements(block);
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
        CoreReq.super.write(writer);
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
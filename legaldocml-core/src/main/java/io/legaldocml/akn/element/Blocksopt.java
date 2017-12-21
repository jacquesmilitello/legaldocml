package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.attribute.CoreOpt;
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
import java.util.stream.Stream;

import static io.legaldocml.akn.element.Groups.blockElements;
import static io.legaldocml.akn.element.Groups.convertSuper;
import static java.util.Objects.requireNonNull;

/**
 * The complex type blocksopt defines the content model and attributes shared by all containers. Here the eId attribute
 * is optional.
 * <p>
 * <pre>
 *   <xsd:complexType name="blocksopt">
 * 	   <xsd:sequence minOccurs="0" maxOccurs="unbounded">
 * 	     <xsd:group ref="blockElements"/>
 * 	   <xsd:sequence>
 * 	   <xsd:attributeGroup ref="coreopt"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class Blocksopt extends AbstractCore implements CoreOpt, BlockElementsContainer<BlockElements> {

    private static final ImmutableMap<String, Supplier<BlockElements>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<BlockElements>>builder()
                .putAll(convertSuper(blockElements()))
                .build();
    }

    // Mandatory (min 1).
    private final AknList<BlockElements> elements = new AknList<>(new BlockElements[4]);

    public boolean removeBlockElements(BlockElements element) {
        return this.elements.remove(requireNonNull(element));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final BlockElements remove(int index) {
        return this.elements.remove(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<BlockElements> stream() {
        return elements.stream();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(BlockElements element) {
        this.elements.add(requireNonNull(element));
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
        CoreOpt.super.write(writer);
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
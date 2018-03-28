package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.attribute.CoreOpt;
import io.legaldocml.akn.container.BlockElementsContainer;
import io.legaldocml.akn.container.PreambleContainersContainer;
import io.legaldocml.akn.group.ANblock;
import io.legaldocml.akn.group.BlockElements;
import io.legaldocml.akn.group.HTMLblock;
import io.legaldocml.akn.group.PreambleContainers;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.element.Groups.blockElements;
import static io.legaldocml.akn.element.Groups.convertSuper;
import static io.legaldocml.akn.element.Groups.preambleContainers;
import static java.util.Objects.requireNonNull;

/**
 * The complex type preambleopt defines the content model and attributes used by preambles. Here the eId attribute is
 * optional.
 *
 * <pre>
 *   <xsd:complexType name="preambleopt">
 * 	   <xsd:choice minOccurs="1" maxOccurs="unbounded">
 * 	     <xsd:group ref="blockElements"/>
 * 		 <xsd:group ref="preambleContainers"/>
 * 	   <xsd:choice>
 * 	   <xsd:attributeGroup ref="coreopt"/>
 *   <xsd:complexType>
 * <pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class Preambleopt extends AbstractCore implements CoreOpt, PreambleContainersContainer<PreambleoptElement>, BlockElementsContainer<PreambleoptElement> {

    private static final ImmutableMap<String, Supplier<PreambleoptElement>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<PreambleoptElement>>builder()
                .putAll(convertSuper(blockElements()))
                .putAll(convertSuper(preambleContainers()))
                .build();
    }

    private final AknList<PreambleoptElement> preambleoptElements = new AknList<>(new PreambleoptElement[4]);

    private void addPreambleoptElement(PreambleoptElement element) {
        this.preambleoptElements.add(requireNonNull(element));
    }

    private boolean removePreambleoptElement(PreambleoptElement element) {
        return this.preambleoptElements.remove(requireNonNull(element));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(BlockElements elements) {
        addPreambleoptElement(elements);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(int index, BlockElements blockElements) {
        this.preambleoptElements.add(index, requireNonNull(blockElements));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(ANblock block) {
        addPreambleoptElement(block);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean remove(ANblock block) {
        return removePreambleoptElement(block);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(HTMLblock block) {
        addPreambleoptElement(block);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(PreambleContainers preambleContainers) {
        addPreambleoptElement(preambleContainers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Container container) {
        addPreambleoptElement(container);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        XmlReaderHelper.read(reader, this.preambleoptElements, ELEMS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        CoreOpt.super.write(writer);
        this.preambleoptElements.write(writer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
       this.preambleoptElements.accept(visitor);
    }

}
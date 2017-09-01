package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
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
import java.util.Objects;
import java.util.function.Supplier;

import static io.legaldocml.akn.element.Groups.basicContainers;
import static io.legaldocml.akn.element.Groups.blockElements;
import static io.legaldocml.akn.element.Groups.convertSuper;
import static java.util.Objects.requireNonNull;

/**
 * the complex type basicopt defines the content model and attributes used by basic containers such as coverPage and
 * conclusions. Here the eId attribute is optional
 *
 * <pre>
 *   <xsd:complexType name="basicopt">
 * 	   <xsd:choice minOccurs="1" maxOccurs="unbounded">
 * 	     <xsd:group ref="blockElements"/>
 * 		 <xsd:group ref="basicContainers"/>
 * 	   <xsd:choice>
 *     <xsd:attributeGroup ref="coreopt"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class Basicopt extends CoreOptImpl implements BlockElementsContainer {

    private static final ImmutableMap<String, Supplier<BasicoptElement>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<BasicoptElement>>builder()
                .putAll(convertSuper(blockElements()))
                .putAll(convertSuper(basicContainers()))
                .build();
    }

    // Mandatory (min 1).
    private final AknList<BasicoptElement> elements = new AknList<>(new BasicoptElement[8]);

    public final void add(BasicoptElement element) {
        this.elements.add(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(BlockElements elements) {
        this.elements.add(Objects.requireNonNull(elements));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(HTMLblock block) {
        this.elements.add(Objects.requireNonNull(block));
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
package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.CoreReq;
import io.legaldocml.akn.attribute.Name;
import io.legaldocml.akn.container.BlockElementsContainer;
import io.legaldocml.akn.group.ANblock;
import io.legaldocml.akn.group.BlockElements;
import io.legaldocml.akn.group.HTMLblock;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.AknElements.CONTAINER;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.element.Groups.blockElements;
import static io.legaldocml.akn.element.Groups.convertSuper;
import static io.legaldocml.akn.util.XmlWriterHelper.writeName;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;
import static java.util.Objects.requireNonNull;

/**
 * The complex type containerType is the content model for the generic element for a container. It can be placed in a
 * container instead of any of the other containers. The attribute name is required and gives a name to the element.
 * <p>
 * <pre>
 *   <xsd:complexType name="containerType">
 * 	   <xsd:choice minOccurs="1" maxOccurs="unbounded">
 * 	     <xsd:group ref="blockElements"/>
 * 		 <xsd:element ref="container"/>
 * 	   <xsd:choice>
 * 	   <xsd:attributeGroup ref="corereq"/>
 * 	   <xsd:attributeGroup ref="name"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class ContainerType extends AbstractCore implements CoreReq, Name, BlockElementsContainer {

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    private static final ImmutableMap<String, Supplier<ContainerElement>> ELEMS;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(AbstractCore.ATTRIBUTES)
                .put(AknAttributes.NAME, biConsumerString(AknAttributes.NAME, getFieldOffset(ContainerType.class, "name")))
                .build();

        ELEMS = ImmutableMap.<String, Supplier<ContainerElement>>builder()
                .putAll(convertSuper(blockElements()))
                .put(CONTAINER, Container::new)
                .build();
    }

    private final AknList<ContainerElement> containerElements = new AknList<>(new ContainerElement[6]);

    private String name;

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(BlockElements elements) {
        this.containerElements.add(requireNonNull(elements));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(ANblock block) {
        this.containerElements.add(requireNonNull(block));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(HTMLblock block) {
        this.containerElements.add(requireNonNull(block));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        XmlReaderHelper.read(reader, this.containerElements, ELEMS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        CoreReq.super.write(writer);
        writeName(writer, this);
        this.containerElements.write(writer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        this.containerElements.accept(visitor);
    }

}
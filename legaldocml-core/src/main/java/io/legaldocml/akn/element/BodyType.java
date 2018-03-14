package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.attribute.CoreOpt;
import io.legaldocml.akn.container.HierElementsContainer;
import io.legaldocml.akn.group.ANhier;
import io.legaldocml.akn.group.HierElements;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.AknElements.COMPONENT_REF;
import static io.legaldocml.akn.element.Groups.convertSuper;
import static io.legaldocml.akn.element.Groups.hierElements;
import static java.util.Objects.requireNonNull;

/**
 * the type bodyType specifies a content model of the main hierarchy of a hierarchical document (e.g, an act or a bill).
 * <p>
 * <pre>
 *   <xsd:complexType name="bodyType">
 *     <xsd:choice minOccurs="1" maxOccurs="unbounded">
 *       <xsd:element ref="componentRef"/>
 *       <xsd:group ref="hierElements"/>
 *     <xsd:choice>
 *     <xsd:attributeGroup ref="coreopt"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class BodyType extends AbstractCore implements CoreOpt, HierElementsContainer<BodyTypeElement> {

    private static final ImmutableMap<String, Supplier<BodyTypeElement>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<BodyTypeElement>>builder()
                .putAll(convertSuper(hierElements()))
                .put(COMPONENT_REF, ComponentRef::new)
                .build();
    }

    // Mandatory (min 1).
    private final AknList<BodyTypeElement> elements = new AknList<>(new BodyTypeElement[4]);

    public final void addBodyTypeElement(BodyTypeElement element) {
        this.elements.add(element);
    }

    public final boolean removeBodyTypeElement(BodyTypeElement element) {
        return this.elements.remove(requireNonNull(element));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(HierElements hier) {
        addBodyTypeElement(requireNonNull(hier));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(HierElements hier) {
        return removeBodyTypeElement(requireNonNull(hier));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(ANhier hier) {
        addBodyTypeElement(requireNonNull(hier));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Hcontainer hcontainer) {
        addBodyTypeElement(requireNonNull(hcontainer));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Hcontainer hcontainer) {
       return removeBodyTypeElement(requireNonNull(hcontainer));
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
package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.attribute.CoreOpt;
import io.legaldocml.akn.container.ContainerElementsContainer;
import io.legaldocml.akn.container.HierElementsContainer;
import io.legaldocml.akn.group.ANhier;
import io.legaldocml.akn.group.ContainerElements;
import io.legaldocml.akn.group.HierElements;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.ListIterable;

import java.io.IOException;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static io.legaldocml.akn.AknElements.CITATION;
import static io.legaldocml.akn.AknElements.CITATIONS;
import static io.legaldocml.akn.AknElements.COVER_PAGE;
import static io.legaldocml.akn.AknElements.FORMULA;
import static io.legaldocml.akn.AknElements.LONG_TITLE;
import static io.legaldocml.akn.AknElements.PREAMBLE;
import static io.legaldocml.akn.AknElements.PREFACE;
import static io.legaldocml.akn.AknElements.RECITAL;
import static io.legaldocml.akn.AknElements.RECITALS;
import static java.util.Objects.requireNonNull;

/**
 * The type portionBodyType specifies a content model of a container of a portion of another document.
 * <p>
 * <pre>
 *   <xsd:complexType name="maincontent">
 *     <xsd:choice>
 *       <xsd:group ref="containerElements"/>
 *       <xsd:group ref="hierElements"/>
 *       <xsd:group ref="ANcontainers"/>
 *       <xsd:element ref="recitals"/>
 *       <xsd:element ref="recital"/>
 *       <xsd:element ref="citations"/>
 *       <xsd:element ref="citation"/>
 *       <xsd:element ref="longTitle"/>
 *       <xsd:element ref="formula"/>
 *       <xsd:element ref="coverPage"/>
 *       <xsd:element ref="preface"/>
 *       <xsd:element ref="preamble"/>
 *     <xsd:choice>
 * 	   <xsd:attributeGroup ref="coreopt"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class PortionBodyType extends AbstractCore implements CoreOpt, ContainerElementsContainer<PortionBodyTypeElement>, HierElementsContainer<PortionBodyTypeElement> {

    private static final ImmutableMap<String, Supplier<PortionBodyTypeElement>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<PortionBodyTypeElement>>builder()
                .putAll(Groups.convertSuper(Groups.containerElements()))
                .putAll(Groups.convertSuper(Groups.hierElements()))
                .putAll(Groups.convertSuper(Groups.ANcontainers()))
                .put(RECITALS, Recitals::new)
                .put(RECITAL, Recital::new)
                .put(CITATIONS, Citations::new)
                .put(CITATION, Citation::new)
                .put(LONG_TITLE, LongTitle::new)
                .put(FORMULA, Formula::new)
                .put(COVER_PAGE, CoverPage::new)
                .put(PREFACE, Preface::new)
                .put(PREAMBLE, Preamble::new)
                .build();
    }

    // Mandatory
    private final AknList<PortionBodyTypeElement> elements = new AknList<>(new PortionBodyTypeElement[4]);

    public final void addPortionBodyTypeElement(PortionBodyTypeElement element) {
        this.elements.add(requireNonNull(element));
    }

    public final boolean removePortionBodyTypeElement(PortionBodyTypeElement element) {
        return this.elements.remove(requireNonNull(element));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final PortionBodyTypeElement remove(int index) {
        return this.elements.remove(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(HierElements hier) {
        addPortionBodyTypeElement(hier);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean remove(HierElements hier) {
        return removePortionBodyTypeElement(hier);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(ANhier hier) {
        addPortionBodyTypeElement(hier);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(Hcontainer hcontainer) {
        addPortionBodyTypeElement(requireNonNull(hcontainer));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean remove(Hcontainer hcontainer) {
        return removePortionBodyTypeElement(requireNonNull(hcontainer));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final ListIterable<PortionBodyTypeElement> iterable() {
        return elements.iterable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(ContainerElements ce) {
        addPortionBodyTypeElement(requireNonNull(ce));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean remove(ContainerElements ce) {
        return removePortionBodyTypeElement(requireNonNull(ce));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(Container container) {
        addPortionBodyTypeElement(requireNonNull(container));
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
    public void read(XmlReader reader) {
        super.read(reader);
        XmlReaderHelper.read(reader, this.elements, ELEMS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        this.elements.accept(visitor);
    }

}
package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.attribute.CoreReq;
import io.legaldocml.akn.container.BlockElementsContainer;
import io.legaldocml.akn.container.HierElementsContainer;
import io.legaldocml.akn.group.ANblock;
import io.legaldocml.akn.group.ANhier;
import io.legaldocml.akn.group.BlockElements;
import io.legaldocml.akn.group.HTMLblock;
import io.legaldocml.akn.group.HierElements;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.AknElements.CITATION;
import static io.legaldocml.akn.AknElements.CITATIONS;
import static io.legaldocml.akn.AknElements.COMPONENT_REF;
import static io.legaldocml.akn.AknElements.CONTENT;
import static io.legaldocml.akn.AknElements.DOCUMENT_REF;
import static io.legaldocml.akn.AknElements.FORMULA;
import static io.legaldocml.akn.AknElements.HEADING;
import static io.legaldocml.akn.AknElements.INTRO;
import static io.legaldocml.akn.AknElements.LONG_TITLE;
import static io.legaldocml.akn.AknElements.NUM;
import static io.legaldocml.akn.AknElements.RECITAL;
import static io.legaldocml.akn.AknElements.RECITALS;
import static io.legaldocml.akn.AknElements.SUB_HEADING;
import static io.legaldocml.akn.AknElements.TD;
import static io.legaldocml.akn.AknElements.TH;
import static io.legaldocml.akn.AknElements.TR;
import static io.legaldocml.akn.AknElements.WRAP_UP;
import static io.legaldocml.akn.element.Groups.ANcontainers;
import static io.legaldocml.akn.element.Groups.blockElements;
import static io.legaldocml.akn.element.Groups.containerElements;
import static io.legaldocml.akn.element.Groups.convertSuper;
import static io.legaldocml.akn.element.Groups.hierElements;
import static java.util.Objects.requireNonNull;

/**
 * The type subFlowStructure specifies the overall content model of the elements that are subFlows.
 * <pre>
 *   <xsd:complexType name="subFlowStructure">
 *     <xsd:choice>
 *       <xsd:group ref="documentType"/>
 *       <xsd:choice minOccurs="1" maxOccurs="unbounded">
 *         <xsd:group ref="blockElements"/>
 *         <xsd:group ref="containerElements"/>
 *         <xsd:element ref="tr"/>
 *         <xsd:element ref="th"/>
 *         <xsd:element ref="td"/>
 *         <xsd:group ref="hierElements"/>
 *         <xsd:element ref="content"/>
 *         <xsd:group ref="ANcontainers"/>
 *         <xsd:element ref="formula"/>
 *         <xsd:element ref="recitals"/>
 *         <xsd:element ref="citations"/>
 *         <xsd:element ref="longTitle"/>
 *         <xsd:element ref="recital"/>
 *         <xsd:element ref="citation"/>
 *         <xsd:element ref="componentRef"/>
 *         <xsd:element ref="documentRef"/>
 *         <xsd:element ref="intro"/>
 *         <xsd:element ref="wrapUp"/>
 *         <xsd:element ref="heading"/>
 *         <xsd:element ref="subheading"/>
 *         <xsd:element ref="num"/>
 *       </xsd:choice>
 *     </xsd:choice>
 *     <xsd:attributeGroup ref="corereq"/>
 *   </xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class SubFlowStructure extends AbstractCore implements CoreReq, BlockElementsContainer<SubFlowStructureElement>, HierElementsContainer<SubFlowStructureElement> {

    private static final ImmutableMap<String, Supplier<SubFlowStructureElement>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<SubFlowStructureElement>>builder()
                .putAll(convertSuper(blockElements()))
                .putAll(convertSuper(containerElements()))
                .put(TR, Tr::new)
                .put(TH, Th::new)
                .put(TD, Td::new)
                .putAll(convertSuper(hierElements()))
                .put(CONTENT, Content::new)
                .putAll(convertSuper(ANcontainers()))
                .put(FORMULA, Formula::new)
                .put(RECITALS, Recitals::new)
                .put(CITATIONS, Citations::new)
                .put(LONG_TITLE, LongTitle::new)
                .put(RECITAL, Recital::new)
                .put(CITATION, Citation::new)
                .put(COMPONENT_REF, ComponentRef::new)
                .put(DOCUMENT_REF, DocumentRef::new)
                .put(INTRO, Intro::new)
                .put(WRAP_UP, WrapUp::new)
                .put(HEADING, Heading::new)
                .put(SUB_HEADING, Subheading::new)
                .put(NUM, Num::new)
                .build();
    }

    // Mandatory (min 1).
    private final AknList<SubFlowStructureElement> elements = new AknList<>(new SubFlowStructureElement[8]);

    public final void addSubFlowStructureElement(SubFlowStructureElement element) {
        this.elements.add(requireNonNull(element));
    }

    public final boolean removeSubFlowStructureElement(SubFlowStructureElement element) {
        return this.elements.remove(requireNonNull(element));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(BlockElements elements) {
        addSubFlowStructureElement(elements);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(int index, BlockElements blockElements) {
        this.elements.add(index, requireNonNull(blockElements));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(HTMLblock block) {
        addSubFlowStructureElement(block);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(HierElements hier) {
        addSubFlowStructureElement(hier);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(HierElements hier) {
        return removeSubFlowStructureElement(requireNonNull(hier));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(ANhier hier) {
        addSubFlowStructureElement(requireNonNull(hier));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(ANblock block) {
        addSubFlowStructureElement(requireNonNull(block));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(ANblock block) {
        return removeSubFlowStructureElement(requireNonNull(block));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Hcontainer hcontainer) {
        removeSubFlowStructureElement(requireNonNull(hcontainer));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Hcontainer hcontainer) {
        return removeSubFlowStructureElement(requireNonNull(hcontainer));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        XmlReaderHelper.read(reader, elements, ELEMS);
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

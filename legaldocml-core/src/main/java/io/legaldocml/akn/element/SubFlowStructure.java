package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
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
public abstract class SubFlowStructure extends CoreReqImpl implements BlockElementsContainer, HierElementsContainer {

    private static final ImmutableMap<String, Supplier<SubFlowStructureElement>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<SubFlowStructureElement>>builder()
                .putAll(convertSuper(blockElements()))
                .putAll(convertSuper(containerElements()))
                .put(Tr.ELEMENT, Tr::new)
                .put(Th.ELEMENT, Th::new)
                .put(Td.ELEMENT, Td::new)
                .putAll(convertSuper(hierElements()))
                .put(CONTENT, Content::new)
                .putAll(convertSuper(ANcontainers()))
                .put(Formula.ELEMENT, Formula::new)
                .put(Recitals.ELEMENT, Recitals::new)
                .put(CITATIONS, Citations::new)
                .put(LongTitle.ELEMENT, LongTitle::new)
                .put(Recital.ELEMENT, Recital::new)
                .put(CITATION, Citation::new)
                .put(COMPONENT_REF, ComponentRef::new)
                .put(DOCUMENT_REF, DocumentRef::new)
                .put(Intro.ELEMENT, Intro::new)
                .put(Wrap.ELEMENT, Wrap::new)
                .put(Heading.ELEMENT, Heading::new)
                .put(SubHeading.ELEMENT, SubHeading::new)
                .put(Num.ELEMENT, Num::new)
                .build();
    }

    // Mandatory (min 1).
    private final AknList<SubFlowStructureElement> elems = new AknList<>(new SubFlowStructureElement[8]);

    public final void add(SubFlowStructureElement element) {
        this.elems.add(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(BlockElements elements) {
        this.elems.add(requireNonNull(elements));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(HTMLblock block) {
        this.elems.add(requireNonNull(block));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(HierElements hier) {
        this.elems.add(requireNonNull(hier));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(ANhier hier) {
        this.elems.add(requireNonNull(hier));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(ANblock block) {
        this.elems.add(requireNonNull(block));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        XmlReaderHelper.read(reader, elems, ELEMS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        super.write(writer);
        this.elems.write(writer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        this.elems.accept(visitor);
    }

}

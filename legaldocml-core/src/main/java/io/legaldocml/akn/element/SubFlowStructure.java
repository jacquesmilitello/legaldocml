package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.element.Groups.ANcontainers;
import static io.legaldocml.akn.element.Groups.blockElements;
import static io.legaldocml.akn.element.Groups.containerElements;
import static io.legaldocml.akn.element.Groups.convertSuper;
import static io.legaldocml.akn.element.Groups.hierElements;

/**
 * The type subFlowStructure specifies the overall content model of the elements that are subFlows.
 * <pre>
 *   &lt;xsd:complexType name="subFlowStructure"&gt;
 *     &lt;xsd:choice&gt;
 *       &lt;xsd:group ref="documentType"/&gt;
 *       &lt;xsd:choice minOccurs="1" maxOccurs="unbounded"&gt;
 *         &lt;xsd:group ref="blockElements"/&gt;
 *         &lt;xsd:group ref="containerElements"/&gt;
 *         &lt;xsd:element ref="tr"/&gt;
 *         &lt;xsd:element ref="th"/&gt;
 *         &lt;xsd:element ref="td"/&gt;
 *         &lt;xsd:group ref="hierElements"/&gt;
 *         &lt;xsd:element ref="content"/&gt;
 *         &lt;xsd:group ref="ANcontainers"/&gt;
 *         &lt;xsd:element ref="formula"/&gt;
 *         &lt;xsd:element ref="recitals"/&gt;
 *         &lt;xsd:element ref="citations"/&gt;
 *         &lt;xsd:element ref="longTitle"/&gt;
 *         &lt;xsd:element ref="recital"/&gt;
 *         &lt;xsd:element ref="citation"/&gt;
 *         &lt;xsd:element ref="componentRef"/&gt;
 *         &lt;xsd:element ref="documentRef"/&gt;
 *         &lt;xsd:element ref="intro"/&gt;
 *         &lt;xsd:element ref="wrapUp"/&gt;
 *         &lt;xsd:element ref="heading"/&gt;
 *         &lt;xsd:element ref="subheading"/&gt;
 *         &lt;xsd:element ref="num"/&gt;
 *       &lt;xsd:choice&gt;
 *     &lt;xsd:choice&gt;
 *     &lt;xsd:attributeGroup ref="corereq"/&gt;
 *   &lt;xsd:complexType&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class SubFlowStructure extends CoreReqImpl {

    private static final ImmutableMap<String, Supplier<SubFlowStructureElement>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<SubFlowStructureElement>>builder()
                .putAll(convertSuper(blockElements()))
                .putAll(convertSuper(containerElements()))
                .put(Tr.ELEMENT, Tr::new)
                .put(Th.ELEMENT, Th::new)
                .put(Td.ELEMENT, Td::new)
                .putAll(convertSuper(hierElements()))
                .put(Content.ELEMENT, Content::new)
                .putAll(convertSuper(ANcontainers()))
                .put(Formula.ELEMENT, Formula::new)
                .put(Recitals.ELEMENT, Recitals::new)
                .put(Citations.ELEMENT, Citations::new)
                .put(LongTitle.ELEMENT, LongTitle::new)
                .put(Recital.ELEMENT, Recital::new)
                .put(Citation.ELEMENT, Citation::new)
                .put(ComponentRef.ELEMENT, ComponentRef::new)
                //.put(DocumentRef.ELEMENT, DocumentRef::new)
                .put(Intro.ELEMENT, Intro::new)
                .put(Wrap.ELEMENT, Wrap::new)
                .put(Heading.ELEMENT, Heading::new)
                .put(SubHeading.ELEMENT, SubHeading::new)
                .put(Num.ELEMENT, Num::new)
                .build();
    }

    // Mandatory (min 1).
    private final AknList<SubFlowStructureElement> elems = new AknList<>(new SubFlowStructureElement[8]);

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
}

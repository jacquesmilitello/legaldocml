package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.AknElements.COMPONENT_REF;
import static io.legaldocml.akn.AknElements.CONTENT;
import static io.legaldocml.akn.element.Groups.ANcontainers;
import static io.legaldocml.akn.element.Groups.blockElements;
import static io.legaldocml.akn.element.Groups.containerElements;
import static io.legaldocml.akn.element.Groups.convertSuper;
import static io.legaldocml.akn.element.Groups.hierElements;

/**
 * <pre>
 * 	<xsd:complexType name="popupStructure">
 * 		<xsd:choice minOccurs="1" maxOccurs="unbounded">
 * 			<xsd:group ref="blockElements"/>
 * 			<xsd:group ref="containerElements"/>
 * 			<xsd:group ref="hierElements"/>
 * 			<xsd:element ref="content"/>
 * 			<xsd:group ref="ANcontainers"/>
 * 			<xsd:element ref="formula"/>
 * 			<xsd:element ref="recitals"/>
 * 			<xsd:element ref="citations"/>
 * 			<xsd:element ref="longTitle"/>
 * 			<xsd:element ref="recital"/>
 * 			<xsd:element ref="citation"/>
 * 			<xsd:element ref="componentRef"/>
 * 			<xsd:element ref="intro"/>
 * 			<xsd:element ref="wrap"/>
 * 			<xsd:element ref="heading"/>
 * 			<xsd:element ref="subheading"/>
 * 			<xsd:element ref="num"/>
 * 		<xsd:choice>
 * 		<xsd:attributeGroup ref="corereq" />
 * 	<xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@Deprecated
public abstract class PopupStructure extends CoreReqImpl {

    private static final ImmutableMap<String, Supplier<PopupStructureElement>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<PopupStructureElement>>builder()
                .putAll(convertSuper(blockElements()))
                .putAll(convertSuper(containerElements()))
                .putAll(convertSuper(hierElements()))
                .put(CONTENT, Content::new)
                .putAll(convertSuper(ANcontainers()))

                .put(COMPONENT_REF, ComponentRef::new)
                .put(Intro.ELEMENT, Intro::new)
                .put(Wrap.ELEMENT, Wrap::new)
                .put(Heading.ELEMENT, Heading::new)
                .put(SubHeading.ELEMENT, SubHeading::new)
                .put(Num.ELEMENT, Num::new)

                .build();
    }


    // Mandatory (min 1).
    private final AknList<PopupStructureElement> elems = new AknList<>(new PopupStructureElement[8]);

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
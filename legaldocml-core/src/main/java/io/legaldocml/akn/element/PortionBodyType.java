package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.AknElements.CITATION;
import static io.legaldocml.akn.AknElements.CITATIONS;
import static io.legaldocml.akn.AknElements.COVER_PAGE;

/**
 * The type portionBodyType specifies a content model of a container of a portion of another document.
 *
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
public abstract class PortionBodyType extends CoreOptImpl {

    private static final ImmutableMap<String, Supplier<PortionBodyTypeElement>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<PortionBodyTypeElement>>builder()
                .putAll(Groups.convertSuper(Groups.containerElements()))
                .putAll(Groups.convertSuper(Groups.hierElements()))
                .putAll(Groups.convertSuper(Groups.ANcontainers()))
                .put(Recitals.ELEMENT, Recitals::new)
                .put(Recital.ELEMENT, Recital::new)
                .put(CITATIONS, Citations::new)
                .put(CITATION, Citation::new)
                .put(LongTitle.ELEMENT, LongTitle::new)
                .put(Formula.ELEMENT, Formula::new)
                .put(COVER_PAGE, CoverPage::new)
                .put(Preface.ELEMENT, Preface::new)
                .put(Preamble.ELEMENT, Preamble::new)
                .build();
    }

    // Mandatory
    private final AknList<PortionBodyTypeElement> elements = new AknList<>(new PortionBodyTypeElement[4]);

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
    public void read(XmlReader reader) {
        super.read(reader);
        XmlReaderHelper.read(reader, this.elements, ELEMS);
    }

}
package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

/**
 * The type portionBodyType specifies a content model of a container of a portion of another document.
 *
 * <pre>
 *   &lt;xsd:complexType name="maincontent"&gt;
 *     &lt;xsd:choice&gt;
 *       &lt;xsd:group ref="containerElements"/&gt;
 *       &lt;xsd:group ref="hierElements"/&gt;
 *       &lt;xsd:group ref="ANcontainers"/&gt;
 *       &lt;xsd:element ref="recitals"/&gt;
 *       &lt;xsd:element ref="recital"/&gt;
 *       &lt;xsd:element ref="citations"/&gt;
 *       &lt;xsd:element ref="citation"/&gt;
 *       &lt;xsd:element ref="longTitle"/&gt;
 *       &lt;xsd:element ref="formula"/&gt;
 *       &lt;xsd:element ref="coverPage"/&gt;
 *       &lt;xsd:element ref="preface"/&gt;
 *       &lt;xsd:element ref="preamble"/&gt;
 *     &lt;xsd:choice&gt;
 * 	   &lt;xsd:attributeGroup ref="coreopt"/&gt;
 *   &lt;xsd:complexType&gt;
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
                .put(Citations.ELEMENT, Citations::new)
                .put(Citation.ELEMENT, Citation::new)
                .put(LongTitle.ELEMENT, LongTitle::new)
                .put(Formula.ELEMENT, Formula::new)
                .put(CoverPage.ELEMENT, CoverPage::new)
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
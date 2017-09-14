package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import javax.xml.stream.XMLStreamConstants;
import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.AknElements.CITATION;
import static io.legaldocml.akn.AknElements.COMPONENT_REF;
import static io.legaldocml.akn.AknElements.INTRO;

/**
 *
 * <pre>
 *   <xsd:complexType name="citationHierarchy">
 *     <xsd:complexContent>
 * 	     <xsd:extension base="basehierarchy">
 * 		   <xsd:sequence>
 * 			 <xsd:element ref="intro" minOccurs="0" maxOccurs="1"/>
 * 			 <xsd:choice minOccurs="1" maxOccurs="unbounded">
 * 		       <xsd:element ref="componentRef"/>
 * 			   <xsd:element ref="citation"/>
 * 			 <xsd:choice>
 * 			 <xsd:element ref="wrapUp" minOccurs="0" maxOccurs="1"/>
 * 		   <xsd:sequence>
 * 		   <xsd:attributeGroup ref="corereq"/>
 * 	     <xsd:extension>
 * 	   <xsd:complexContent>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class CitationHierarchy extends BaseHierarchyCoreReq  {

    private static final ImmutableMap<String, Supplier<CitationHierarchyElement>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<CitationHierarchyElement>>builder()
                .put(COMPONENT_REF, ComponentRef::new)
                .put(CITATION, Citation::new)
                .build();
    }

    private Intro intro;
    private final AknList<CitationHierarchyElement> elements = new AknList<>(new CitationHierarchyElement[4]);
    private WrapUp wrapUp;

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        super.write(writer);

        if (this.intro != null) {
            this.intro.write(writer);
        }
        if (this.elements.size() > 0) {
            this.elements.write(writer);
        }

        if (this.wrapUp != null) {
            this.wrapUp.write(writer);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {

        QName qName = reader.getQName();
        super.read(reader);

//        if (LOGGER.isTraceEnabled()) {
//            LOGGER.trace("Hierarchy -> [{}] -> [{}]" , getClass().getSimpleName(), this);
//        }

        if (reader.getQName().equalsLocalName(INTRO)) {
            this.intro = new Intro();
            this.intro.read(reader);
            reader.nextStartOrEndElement();
        }

        XmlReaderHelper.read(reader, this.elements, ELEMS, qName, Wrap.ELEMENT);


        if (reader.<AkomaNtosoContext>getContext().getAkoXmlModule().getVersion() == 3 && reader.getEventType() == XMLStreamConstants.START_ELEMENT && reader.getQName().equalsLocalName(WrapUp.ELEMENT)) {
            this.wrapUp = new WrapUp();
            this.wrapUp.read(reader);
            reader.nextStartOrEndElement();
        }
    }

    public final void setIntro(Intro intro) {
        this.intro = intro;
    }

}
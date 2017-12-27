package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.akn.attribute.CoreReq;
import io.legaldocml.akn.container.HierElementsContainer;
import io.legaldocml.akn.group.ANhier;
import io.legaldocml.akn.group.HierElements;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.diff.DiffContext;
import io.legaldocml.diff.Diffs;
import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamConstants;
import java.io.IOException;
import java.lang.Object;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static io.legaldocml.akn.AknElements.COMPONENT_REF;
import static io.legaldocml.akn.AknElements.CONTENT;
import static io.legaldocml.akn.AknElements.CROSS_HEADING;
import static io.legaldocml.akn.AknElements.INTRO;
import static io.legaldocml.akn.AknElements.WRAP;
import static io.legaldocml.akn.AknElements.WRAP_UP;
import static io.legaldocml.akn.element.Groups.convertSuper;
import static io.legaldocml.akn.element.Groups.hierElements;
import static io.legaldocml.util.Equals.doEquals;
import static java.util.Objects.requireNonNull;

/**
 * The complex type hierarchy is used by most or all the hierarchical elements of act-like documents.
 * <p>
 * <pre>
 *   <xsd:complexType name="hierarchy">
 *     <xsd:complexContent>
 * 	     <xsd:extension base="basehierarchy">
 * 		   <xsd:choice>
 * 		     <xsd:sequence>
 * 			   <xsd:element ref="intro" minOccurs="0" maxOccurs="1"/>
 * 			   <xsd:choice minOccurs="0" maxOccurs="unbounded">
 * 			     <xsd:element ref="componentRef"/>
 * 				 <xsd:group ref="hierElements"/>
 * 				 <xsd:element ref="crossHeading"/>
 * 			   </xsd:choice>
 * 			   <xsd:element ref="wrapUp" minOccurs="0" maxOccurs="1"/>
 * 			   </xsd:sequence>
 * 		     <xsd:element ref="content"/>
 * 		   </xsd:choice>
 * 		   <xsd:attributeGroup ref="corereq"/>
 * 	     </xsd:extension>
 * 	   </xsd:complexContent>
 *   </xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class Hierarchy extends BaseHierarchy implements CoreReq, HierElementsContainer<HierarchyElement> {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Hierarchy.class);

    public static final ImmutableMap<String, Supplier<HierarchyElement>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<HierarchyElement>>builder()
                .putAll(convertSuper(hierElements()))
                .put(COMPONENT_REF, ComponentRef::new)
                .put(CROSS_HEADING, CrossHeading::new)
                .build();
    }

    private Content content;
    // or
    private Intro intro;
    private AknList<HierarchyElement> elements;

    @Deprecated
    private Wrap wrap;

    private WrapUp wrapUp;

    public final void addHierarchyElement(HierarchyElement element) {
        if (this.elements == null) {
            this.elements = new AknList<>(new HierarchyElement[4]);
        }
        this.elements.add(element);
    }

    public final boolean removeHierarchyElement(HierarchyElement element) {
        return this.elements != null && this.elements.remove(requireNonNull(element));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final HierarchyElement remove(int index) {
        return this.elements.remove(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(HierElements hier) {
        addHierarchyElement(hier);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(HierElements hier) {
        return removeHierarchyElement(hier);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(ANhier hier) {
        addHierarchyElement(hier);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<HierarchyElement> stream() {
        return (this.elements == null) ? Stream.empty() : this.elements.stream();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        CoreReq.super.write(writer);
        super.write(writer);
        if (this.content != null) {
            this.content.write(writer);
        } else {
            if (this.intro != null) {
                this.intro.write(writer);
            }
            if (this.elements != null && this.elements.size() > 0) {
                this.elements.write(writer);
            }

            if (writer.getVersion() == 2) {
                if (this.wrap != null) {
                    this.wrap.write(writer);
                }
            } else {
                if (this.wrapUp != null) {
                    this.wrapUp.write(writer);
                }
            }
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

        if (reader.getQName().equalsLocalName(CONTENT)) {
            this.content = new Content();
            this.content.read(reader);
            reader.nextStartOrEndElement();
            return;
        }

        if (reader.getQName().equalsLocalName(INTRO)) {
            this.intro = new Intro();
            this.intro.read(reader);
            reader.nextStartOrEndElement();
        }

        this.elements = new AknList<>(new HierarchyElement[4]);

        if (reader.<AkomaNtosoContext>getContext().getAkoXmlModule().getVersion() == 2) {

            XmlReaderHelper.read(reader, this.elements, ELEMS, qName, WRAP);

            if (reader.getEventType() == XMLStreamConstants.START_ELEMENT && reader.getQName().equalsLocalName(WRAP)) {
                this.wrap = new Wrap();
                this.wrap.read(reader);
                reader.nextStartOrEndElement();
            }

        } else {

            XmlReaderHelper.read(reader, this.elements, ELEMS, qName, WRAP_UP);

            if (reader.getEventType() == XMLStreamConstants.START_ELEMENT && reader.getQName().equalsLocalName(WRAP_UP)) {
                this.wrapUp = new WrapUp();
                this.wrapUp.read(reader);
                reader.nextStartOrEndElement();
            }

        }

    }

    public final Content getContent() {
        return this.content;
    }

    public final void setContent(Content content) {
        this.content = content;
    }

    public final void setIntro(Intro intro) {
        this.intro = intro;
    }

    public final Intro getIntro() {
        return this.intro;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (this.content != null) {
            this.content.accept(visitor);
        } else {
            if (this.intro != null) {
                this.intro.accept(visitor);
            }
            if (this.elements != null) {
                this.elements.accept(visitor);
            }
            if (this.wrapUp != null) {
                this.wrapUp.accept(visitor);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object obj) {
        return doEquals(this, obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nestedCompare(AknObject object, DiffContext context) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("nested Compare left=[{}], right=[{}] ", this, object);
        }

        Hierarchy hierarchy = (Hierarchy) object;

        // compare nested HierarchyElement
        Diffs.compareNullable(this.elements, hierarchy.elements, context);

    }
}
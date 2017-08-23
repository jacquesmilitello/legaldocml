package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import javax.xml.stream.XMLStreamConstants;
import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.element.Groups.blockElements;
import static io.legaldocml.akn.element.Groups.convertSuper;

/**
 * The complex type hierarchy is used by most or all the hierarchical elements of act-like documents.
 *
 * <pre>
 *   <xsd:complexType name="blockContainerType">
 *     <xsd:complexContent>
 *       <xsd:extension base="basehierarchy">
 *         <xsd:sequence>
 *           <xsd:element ref="intro" minOccurs="0" maxOccurs="1"/>
 *           <xsd:choice minOccurs="1" maxOccurs="unbounded">
 *             <xsd:element ref="componentRef"/>
 *             <xsd:group ref="blockElements"/>
 *           <xsd:choice>
 *           <xsd:element ref="wrapUp" minOccurs="0" maxOccurs="1"/>
 *         <xsd:sequence>
 *         <xsd:attributeGroup ref="corereq"/>
 *       <xsd:extension>
 *     <xsd:complexContent>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class BlockContainerType extends BaseHierarchyCoreReq {

    private static final ImmutableMap<String, Supplier<BlockContainerTypeElement>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<BlockContainerTypeElement>>builder()
                .putAll(convertSuper(blockElements()))
                .put(ComponentRef.ELEMENT, ComponentRef::new)
                .build();
    }

    private Intro intro;
    private final AknList<BlockContainerTypeElement> elements = new AknList<>(new BlockContainerTypeElement[4]);
    private WrapUp wrapUp;

    public final void setIntro(Intro intro) {
        this.intro = intro;
    }

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

        if (reader.getQName().equalsLocalName(Intro.ELEMENT)) {
            this.intro = new Intro();
            this.intro.read(reader);
            reader.nextStartOrEndElement();
        }

        XmlReaderHelper.read(reader, this.elements, ELEMS, qName, WrapUp.ELEMENT);


        if (reader.getEventType() == XMLStreamConstants.START_ELEMENT && reader.getQName().equalsLocalName(WrapUp.ELEMENT)) {
            this.wrapUp = new WrapUp();
            this.wrapUp.read(reader);
            reader.nextStartOrEndElement();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (this.intro != null) {
            this.intro.accept(visitor);
        }
        this.elements.accept(visitor);
        if (this.wrapUp != null) {
            this.elements.accept(visitor);
        }
    }


}
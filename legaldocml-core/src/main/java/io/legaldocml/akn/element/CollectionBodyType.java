package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.akn.CollectionBodyElement;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.AknElements.COMPONENT;
import static io.legaldocml.akn.AknElements.COMPONENT_REF;
import static io.legaldocml.akn.AknElements.INTERSTITIAL;
import static io.legaldocml.akn.element.Groups.convertSuper;

/**
 * the type collectionBodyType specifies a content model of a container of a list of other documents (e.g, acts, bills,
 * amendments, etc.) possibly  interspersed with interstitial elements with content that does not form an individual
 * document.
 *
 * <pre>
 *   <xsd:complexType name="collectionBodyType">
 *     <xsd:sequence>
 *       <xsd:element ref="component" minOccurs="1" maxOccurs="unbounded"/>
 *     <xsd:sequence>
 *     <xsd:attributeGroup ref="coreopt"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class CollectionBodyType extends CoreOptImpl {

    @Deprecated
    private static final ImmutableMap<String, Supplier<CollectionBodyElement>> COLLECTION_BODY_TYPE_V2;

    static {
        COLLECTION_BODY_TYPE_V2 = ImmutableMap.<String, Supplier<CollectionBodyElement>>builder()
                .put(COMPONENT_REF, ComponentRef::new)
                .put(INTERSTITIAL, Interstitial::new)
                .putAll(convertSuper(Groups.DOCUMENT_TYPE_V2))
                .build();
    }


    // Mandatory (min 1)
    private final AknList<Component> components = new AknList<>(new Component[4]);

    // Mandatory (min 1)
    private final AknList<CollectionBodyElement> elements = new AknList<>(new CollectionBodyElement[4]);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        super.write(writer);
        if (writer.getVersion() == 2) {
            this.elements.write(writer);
        } else {
            this.components.write(writer);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        if (reader.<AkomaNtosoContext>getContext().getAkoXmlModule().getVersion() == 2) {
            XmlReaderHelper.read(reader, elements, COLLECTION_BODY_TYPE_V2);
        } else {
            reader.nextStartOrEndElement();
            if (reader.getQName().equalsLocalName(COMPONENT)) {
                Component component;
                do {
                    component = new Component();
                    component.read(reader);
                    this.components.add(component);
                    reader.nextStartOrEndElement();
                } while (reader.getQName().equalsLocalName(COMPONENT));
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        this.components.accept(visitor);
    }

}
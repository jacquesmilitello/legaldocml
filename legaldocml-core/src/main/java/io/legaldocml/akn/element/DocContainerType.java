package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.DocContainerTypeElement;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

/**
 * The complex type docContainerType defines a shared content model for elements that contain whole documents, namely
 * attachment, collectionItem, component.
 *
 * <pre>
 *   &lt;xsd:complexType name="docContainerType"&gt;
 *     &lt;xsd:complexContent&gt;
 * 	     &lt;xsd:extension base="basehierarchy"&gt;
 * 		   &lt;xsd:choice minOccurs="0" maxOccurs="unbounded"&gt;
 * 		     &lt;xsd:group ref="documentType" /&gt;
 * 			 &lt;xsd:element ref="interstitial" /&gt;
 * 			 &lt;xsd:element ref="toc"/&gt;
 * 			 &lt;xsd:element ref="documentRef"/&gt;
 * 		   &lt;xsd:choice&gt;
 *         &lt;xsd:attributeGroup ref="corereq" /&gt;
 *       &lt;xsd:extension&gt;
 *     &lt;xsd:complexContent&gt;
 *   &lt;xsd:complexType&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class DocContainerType extends BaseHierarchyCoreReq {

    private static final ImmutableMap<String, Supplier<DocContainerTypeElement>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<DocContainerTypeElement>>builder()
                .putAll(Groups.convertSuper(Groups.DOCUMENT_TYPE))
                .put(Interstitial.ELEMENT, Interstitial::new)
                .put(Toc.ELEMENT, Toc::new)
                .put(DocumentRef.ELEMENT, DocumentRef::new)
                .build();
    }

    // Optional.
    private AknList<DocContainerTypeElement> elements;

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        super.write(writer);
        if (this.elements != null) {
            this.elements.write(writer);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        QName parent = reader.getQName();
        super.read(reader);
        this.elements = new AknList<>(new DocContainerTypeElement[4]);
        XmlReaderHelper.read(reader, this.elements, ELEMS, parent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (this.elements != null) {
            this.elements.accept(visitor);
        }
    }

}
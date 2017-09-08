package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.AknElements.COMPONENT_REF;

/**
 * The complex type hierarchy is used by most or all the hierarchical elements of act-like documents.
 *
 * <pre>
 *   <xsd:complexType name="althierarchy">
 * 	   <xsd:complexContent>
 * 	     <xsd:extension base="basehierarchy">
 * 		   <xsd:choice minOccurs="0" maxOccurs="unbounded">
 * 		     <xsd:group ref="containerElements" />
 * 			 <xsd:element ref="componentRef" />
 * 			 <xsd:group ref="ANcontainers" />
 * 			 <xsd:group ref="blockElements" />
 * 		   <xsd:choice>
 * 		 <xsd:attributeGroup ref="corereq" />
 * 	     <xsd:extension>
 * 	   <xsd:complexContent>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class AltHierarchy extends BaseHierarchyCoreReq {

    private static final ImmutableMap<String, Supplier<AltHierarchyElement>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<AltHierarchyElement>>builder()
                .putAll(Groups.convertSuper(Groups.containerElements()))
                .putAll(Groups.convertSuper(Groups.ANcontainers()))
                .putAll(Groups.convertSuper(Groups.blockElements()))
                .put(COMPONENT_REF, ComponentRef::new)
                .build();
    }

    // Optional.
    private AknList<AltHierarchyElement> elements;

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
        this.elements = new AknList<>(new AltHierarchyElement[4]);
        XmlReaderHelper.read(reader, this.elements, ELEMS, parent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        super.accept(visitor);
        if (this.elements != null) {
            this.elements.accept(visitor);
        }
    }

}
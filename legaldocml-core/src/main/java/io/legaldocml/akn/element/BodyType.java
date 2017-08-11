package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.element.Groups.convertSuper;
import static io.legaldocml.akn.element.Groups.hierElements;

/**
 * the type bodyType specifies a content model of the main hierarchy of a hierarchical document (e.g, an act or a bill).
 * <p/>
 * <pre>
 *   <xsd:complexType name="bodyType">
 *     <xsd:choice minOccurs="1" maxOccurs="unbounded">
 *       <xsd:element ref="componentRef"/>
 *       <xsd:group ref="hierElements"/>
 *     </xsd:choice>
 *     <xsd:attributeGroup ref="coreopt"/>
 *   </xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class BodyType extends CoreOptImpl {

    private static final ImmutableMap<String, Supplier<BodyTypeElement>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<BodyTypeElement>>builder()
                .putAll(convertSuper(hierElements()))
                .put(ComponentRef.ELEMENT, ComponentRef::new)
                .build();
    }

    // Mandatory (min 1).
    private final AknList<BodyTypeElement> elements = new AknList<>(new BodyTypeElement[4]);

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        XmlReaderHelper.read(reader, this.elements, ELEMS);
    }

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
    public void accept(AknVisitor visitor) {
        this.elements.accept(visitor);
    }

}
package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.element.Groups.blockElements;
import static io.legaldocml.akn.element.Groups.convertSuper;
import static io.legaldocml.akn.element.Groups.preambleContainers;

/**
 * The complex type preambleopt defines the content model and attributes used by preambles. Here the eId attribute is
 * optional.
 *
 * <pre>
 *   <xsd:complexType name="preambleopt">
 * 	   <xsd:choice minOccurs="1" maxOccurs="unbounded">
 * 	     <xsd:group ref="blockElements"/>
 * 		 <xsd:group ref="preambleContainers"/>
 * 	   <xsd:choice>
 * 	   <xsd:attributeGroup ref="coreopt"/>
 *   <xsd:complexType>
 * <pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class Preambleopt extends CoreOptImpl {

    private static final ImmutableMap<String, Supplier<PreambleoptElement>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<PreambleoptElement>>builder()
                .putAll(convertSuper(blockElements()))
                .putAll(convertSuper(preambleContainers()))
                .build();
    }

    private final AknList<PreambleoptElement> elements = new AknList<>(new PreambleoptElement[4]);

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        XmlReaderHelper.read(reader, elements, ELEMS);
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
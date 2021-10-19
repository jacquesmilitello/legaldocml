package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknElements;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.CoreOpt;
import io.legaldocml.akn.attribute.ValueOpt;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.AknAttributes.VALUE;
import static io.legaldocml.akn.AknElements.LI;
import static io.legaldocml.akn.AknElements.OL;
import static io.legaldocml.akn.AknElements.UL;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.akn.element.Groups.convertSuper;
import static io.legaldocml.akn.element.Groups.inlineCM;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element li is an HTML element and is used in Akoma Ntoso as in HTML, for the generic list item (not a pattern).
 * <p>
 * <pre>
 *   <xsd:element name="li">
 * 	   <xsd:complexType mixed="true">
 * 	     <xsd:choice minOccurs="0" maxOccurs="unbounded">
 * 		   <xsd:group ref="inlineCM"/>
 * 		   <xsd:element ref="ul"/>
 *         <xsd:element ref="ol"/>
 * 	       <xsd:element ref="p"/>
 * 	     </xsd:choice>
 * 	     <xsd:attributeGroup ref="optvalue"/>
 * 	     <xsd:attributeGroup ref="coreopt"/>
 * 	   </xsd:complexType>
 *   </xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Li extends AbstractCore implements CoreOpt, ValueOpt {

    /**
     * Memory address.
     */
    private static final long ADDRESS_LI = Buffers.address(LI);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    private static final ImmutableMap<String, Supplier<LiElement>> ELEMS;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(AbstractCore.ATTRIBUTES)
                .put(VALUE, attributeGetterSetter4String(VALUE, getFieldOffset(Li.class, "value")))
                .build();

        ELEMS = ImmutableMap.<String, Supplier<LiElement>>builder()
                .putAll(convertSuper(inlineCM()))
                .put(UL, Ul::new)
                .put(OL, Ol::new)
                .put(AknElements.P, P::new)
                .build();
    }

    private final AknList<LiElement> elements = new AknList<>(new LiElement[2]);

    private String value;

    /**
     * {@inheritDoc}
     */
    public String getValue() {
        return this.value;
    }

    /**
     * {@inheritDoc}
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return LI;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        XmlReaderHelper.readWithCharacters(reader, this, this.elements, ELEMS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_LI, 2);
        CoreOpt.super.write(writer);
        ValueOpt.super.write(writer);
        this.elements.write(writer);
        writer.writeEnd(ADDRESS_LI, 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }

}
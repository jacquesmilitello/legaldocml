package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.ValueOpt;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.element.Groups.convertSuper;
import static io.legaldocml.akn.element.Groups.inlineCM;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element li is an HTML element and is used in Akoma Ntoso as in HTML, for the generic list item (not a pattern).
 * <p/>
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
public final class Li extends CoreOptImpl implements ValueOpt {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "li";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    private static final ImmutableMap<String, Supplier<LiElement>> ELEMS;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(CoreOptImpl.ATTRIBUTES)
                .put(ValueOpt.ATTRIBUTE, biConsumerString(getFieldOffset(Li.class, "value")))
                .build();

        ELEMS = ImmutableMap.<String, Supplier<LiElement>>builder()
                .putAll(convertSuper(inlineCM()))
                .put(Ul.ELEMENT, Ul::new)
                .put(Ol.ELEMENT, Ol::new)
                .put(P.ELEMENT, P::new)
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
        return ELEMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        XmlReaderHelper.readWithCharacters(reader, this.elements, ELEMS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 2);
        super.write(writer);
        this.elements.write(writer);
        writer.writeEnd(ADDRESS, 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }

}
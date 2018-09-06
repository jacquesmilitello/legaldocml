package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Name;
import io.legaldocml.akn.group.InlineElements;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.NAME;
import static io.legaldocml.akn.AknElements.INLINE;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element inline is a generic element for an inline. It can be placed inside a block instead of any of the other
 * inlines. The attribute name is required and gives a name to the element.
 * <p>
 * <pre>
 *   <xsd:element name="inline">
 * 	   <xsd:complexType mixed="true">
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="inline">
 * 		     <xsd:attributeGroup ref="name"/>
 * 	   	   </xsd:extension>
 *       </xsd:complexContent>
 * 	   </xsd:complexType>
 *   </xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Inline extends InlineType implements Name, InlineElements {

    /**
     * Memory address.
     */
    private static final long ADDRESS_INLINE = Buffers.address(INLINE);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(InlineType.ATTRIBUTES)
                .put(NAME, attributeGetterSetter4String(NAME, getFieldOffset(Inline.class, "name")))
                .build();
    }

    private String name;

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_INLINE, 6);
        Name.super.write(writer);
        super.write(writer);
        writer.writeEnd(ADDRESS_INLINE, 6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return INLINE;
    }

    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }

}
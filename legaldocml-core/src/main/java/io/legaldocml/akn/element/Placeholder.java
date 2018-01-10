package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.OriginalText;
import io.legaldocml.akn.group.ANinline;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.ORIGINAL_TEXT;
import static io.legaldocml.akn.AknElements.PLACE_HOLDER;
import static io.legaldocml.akn.util.XmlWriterHelper.writeOriginalText;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * the element placeholder is an inline element containing the text of a computable expression (e.g., '30 days after the
 * publication of this act') that can be replaced editorially with an actual value.
 * <p>
 * <pre>
 *   <xsd:element name="placeholder">
 * 	   <xsd:complexType mixed="true">
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="inline">
 * 		     <xsd:attributeGroup ref="originalText"/>
 * 		   <xsd:extension>
 * 		 <xsd:complexContent>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Placeholder extends InlineType implements OriginalText, ANinline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_PLACE_HOLDER = Buffers.address(PLACE_HOLDER);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(InlineType.ATTRIBUTES)
                .put(ORIGINAL_TEXT, Attributes.attributeGetterSetter4Uri(ORIGINAL_TEXT, getFieldOffset(Placeholder.class, "originalText")))
                .build();
    }

    private String originalText;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getOriginalText() {
        return originalText;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_PLACE_HOLDER, 11);
        writeOriginalText(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_PLACE_HOLDER, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return PLACE_HOLDER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }

}
package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.AknObject;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;
import static io.legaldocml.unsafe.UnsafeString.getChars;

/**
 * The element quotedStructure is a popup element containing a full structure proposed as an insertion or a replacement.
 * Use attribute for when quotedStructure is used in a mmod or rmod to point to the id of the corresponding refelement.
 *
 * <pre>
 * 	<xsd:element name="quotedStructure">
 * 		<xsd:complexType>
 * 			<xsd:complexContent>
 * 				<xsd:extension base="popupStructure">
 * 					<xsd:attribute name="for" type="xsd:anyURI" />
 * 				<xsd:extension>
 * 			<xsd:complexContent>
 * 		<xsd:complexType>
 * 	<xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class QuotedStructureV2 extends PopupStructure implements QuotedStructure {

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(PopupStructure.ATTRIBUTES)
                .put(AknAttributes.FOR, biConsumerString(getFieldOffset(QuotedStructureV2.class, "_for")))
                .build();

    }

    // Optional
    private String _for;

    public String getFor() {
        return this._for;
    }

    public void setFor(String _for) {
        this._for = _for;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 15);
        if (this._for != null) {
            writer.writeAttribute(Attributes.ADDRESS_FOR, 3, getChars(this._for));
        }
        super.write(writer);
        writer.writeEnd(ADDRESS, 15);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }
}
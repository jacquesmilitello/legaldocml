package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.FOR;
import static io.legaldocml.akn.AknElements.QUOTED_STRUCTURE;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;
import static io.legaldocml.unsafe.UnsafeString.getChars;

/**
 * The element quotedStructure is a popup element containing a full structure proposed as an insertion or a replacement.
 * Use attribute for when quotedStructure is used in a mmod or rmod to point to the id of the corresponding refelement.
 * <p>
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
    private static final long ADDRESS_QUOTED_STRUCTURE = Buffers.address(QUOTED_STRUCTURE);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(PopupStructure.ATTRIBUTES)
                .put(FOR, biConsumerString(FOR, getFieldOffset(QuotedStructureV2.class, "_for")))
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
        writer.writeStart(ADDRESS_QUOTED_STRUCTURE, 15);
        if (this._for != null) {
            writer.writeAttribute(Attributes.ADDRESS_FOR, 3, getChars(this._for));
        }
        super.write(writer);
        writer.writeEnd(ADDRESS_QUOTED_STRUCTURE, 15);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }
}
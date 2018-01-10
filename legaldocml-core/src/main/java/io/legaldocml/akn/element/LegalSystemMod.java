package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.LegalSystemModType;
import io.legaldocml.akn.type.LegalSystemMods;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.TYPE;
import static io.legaldocml.akn.AknElements.LEGAL_SYSTEM_MOD;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4Enum;
import static io.legaldocml.akn.util.XmlWriterHelper.writeLegalSystemMods;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element legalSystemMod is a metadata element specifying an (active or passive) modification in the legal system
 * for the document.
 *
 * <pre>
 *   <xsd:element name="legalSystemMod">
 * 	   <xsd:complexType>
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="modificationType">
 * 		     <xsd:attributeGroup ref="legalSystemModType"/>
 * 		   </xsd:extension>
 * 	     </xsd:complexContent>
 * 	   </xsd:complexType>
 *   </xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class LegalSystemMod extends ModificationType implements LegalSystemModType, AmendmentsElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_LEGAL_SYSTEM_MOD = Buffers.address(LEGAL_SYSTEM_MOD);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(ModificationType.ATTRIBUTES)
                .put(TYPE, attributeGetterSetter4Enum(TYPE, getFieldOffset(LegalSystemMod.class, "type"), LegalSystemMods.class))
                .build();
    }

    private LegalSystemMods type;

    /**
     * {@inheritDoc}
     */
    @Override
    public LegalSystemMods getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setType(LegalSystemMods type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_LEGAL_SYSTEM_MOD, 11);
        writeLegalSystemMods(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_LEGAL_SYSTEM_MOD, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return LEGAL_SYSTEM_MOD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }
}
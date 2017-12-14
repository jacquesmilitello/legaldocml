package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.EfficacyModType;
import io.legaldocml.akn.type.EfficacyMods;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.TYPE;
import static io.legaldocml.akn.AknElements.EFFICACY_MOD;
import static io.legaldocml.akn.element.Attributes.biConsumerEnum;
import static io.legaldocml.akn.util.XmlWriterHelper.writeEfficacyMods;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element efficacyMod is a metadata element specifying an (active or passive) modification in efficacy for the
 * document.
 * <p>
 * <pre>
 *   <xsd:element name="efficacyMod">
 * 	   <xsd:complexType>
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="modificationType">
 * 		     <xsd:attributeGroup ref="efficacyModType"/>
 * 		   </xsd:extension>
 * 	     </xsd:complexContent>
 * 	   </xsd:complexType>
 *   </xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class EfficacyMod extends ModificationType implements EfficacyModType, AmendmentsElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_EFFICACY_MOD = Buffers.address(EFFICACY_MOD);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(ModificationType.ATTRIBUTES)
                .put(TYPE, biConsumerEnum(TYPE, getFieldOffset(EfficacyMod.class, "type"), EfficacyMods.class))
                .build();
    }

    private EfficacyMods type;

    public EfficacyMods getType() {
        return this.type;
    }

    public void setType(EfficacyMods type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_EFFICACY_MOD, 11);
        writeEfficacyMods(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_EFFICACY_MOD, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return EFFICACY_MOD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }
}
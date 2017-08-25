package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.EfficacyModType;
import io.legaldocml.akn.type.EfficacyMods;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerEnum;
import static io.legaldocml.akn.util.XmlWriterHelper.writeEfficacyMods;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element efficacyMod is a metadata element specifying an (active or passive) modification in efficacy for the
 * document.
 *
 * <pre>
 *   <xsd:element name="efficacyMod">
 * 	   <xsd:complexType>
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="modificationType">
 * 		     <xsd:attributeGroup ref="efficacyModType"/>
 * 		   <xsd:extension>
 * 	     <xsd:complexContent>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class EfficacyMod extends ModificationType implements EfficacyModType, AmendmentsElement {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "efficacyMod";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(ModificationType.ATTRIBUTES)
                .put(AknAttributes.TYPE, biConsumerEnum(getFieldOffset(EfficacyMod.class, "type"), EfficacyMods.class))
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
        writer.writeStart(ADDRESS, 11);
        writeEfficacyMods(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS, 11);
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
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }
}
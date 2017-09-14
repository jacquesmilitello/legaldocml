package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.attribute.ForceModType;
import io.legaldocml.akn.type.ForceMods;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.AknElements.FORCE_MOD;
import static io.legaldocml.akn.element.Attributes.ADDRESS_TYPE;
import static io.legaldocml.akn.element.Attributes.biConsumerEnum;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;
import static io.legaldocml.unsafe.UnsafeString.getChars;

/**
 * The element forceMod is a metadata element specifying an (active or passive) modification in force for the document.
 *
 * <pre>
 *   <xsd:element name="forceMod">
 * 	   <xsd:complexType>
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="modificationType">
 * 		     <xsd:attributeGroup ref="forceModType"/>
 * 		 <xsd:extension>
 * 	     <xsd:complexContent>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ForceMod extends ModificationType implements ForceModType, AmendmentsElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_FORCE_MOD = Buffers.address(FORCE_MOD);

    protected static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(ModificationType.ATTRIBUTES)
                .put(AknAttributes.TYPE, biConsumerEnum(getFieldOffset(ForceMod.class, "type"), ForceMods.class))
                .build();
    }

    private ForceMods type;

    /**
     * {@inheritDoc}
     */
    @Override
    public ForceMods getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setType(ForceMods type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_FORCE_MOD, 8);
        if (this.type != null) {
            writer.writeAttribute(ADDRESS_TYPE, 4, getChars(this.type.name()));
        }
        super.write(writer);
        writer.writeEnd(ADDRESS_FORCE_MOD, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return FORCE_MOD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }

}
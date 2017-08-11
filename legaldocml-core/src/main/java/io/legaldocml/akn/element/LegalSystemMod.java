package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.LegalSystemModType;
import io.legaldocml.akn.type.LegalSystemMods;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerEnum;
import static io.legaldocml.akn.util.XmlWriterHelper.writeLegalSystemMods;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element legalSystemMod is a metadata element specifying an (active or passive) modification in the legal system
 * for the document.
 * <p/>
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
     * XML Tag element name.
     */
    public static final String ELEMENT = "legalSystemMod";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(ModificationType.ATTRIBUTES)
                .put(LegalSystemModType.ATTRIBUTE, biConsumerEnum(getFieldOffset(LegalSystemMod.class, "type"), LegalSystemMods.class))
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
        writer.writeStart(ADDRESS, 11);
        writeLegalSystemMods(writer, this);
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
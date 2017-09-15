package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.attribute.MeaningModType;
import io.legaldocml.akn.type.MeaningMods;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.AknElements.DOMAIN;
import static io.legaldocml.akn.AknElements.MEANING_MOD;
import static io.legaldocml.akn.element.Attributes.biConsumerEnum;
import static io.legaldocml.akn.util.XmlWriterHelper.writeMeaningModType;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element meaningMod is a metadata element specifying an (active or passive) modification in meaning for the
 * document.
 *
 * <pre>
 *   <xsd:element name="meaningMod">
 * 	   <xsd:complexType>
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="modificationType">
 * 		     <xsd:sequence>
 * 			   <xsd:element ref="domain" minOccurs="0" maxOccurs="1"/>
 * 			 <xsd:sequence>
 * 			 <xsd:attributeGroup ref="meaningModType"/>
 * 		   <xsd:extension>
 * 		 <xsd:complexContent>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class MeaningMod extends ModificationType implements MeaningModType, AmendmentsElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_MEANING_MOD = Buffers.address(MEANING_MOD);

    protected static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {

        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(ModificationType.ATTRIBUTES)
                .put(AknAttributes.TYPE, biConsumerEnum(getFieldOffset(MeaningMod.class, "type"), MeaningMods.class))
                .build();
    }


    private MeaningMods type;
    private Domain domain;

    /**
     * {@inheritDoc}
     */
    @Override
    public MeaningMods getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setType(MeaningMods type) {
        this.type = type;
    }

    public Domain getDomain() {
        return this.domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_MEANING_MOD, 10);
        writeMeaningModType(writer, this);
        super.write(writer);
        if (this.domain != null) {
            this.domain.write(writer);
        }
        writer.writeEnd(ADDRESS_MEANING_MOD, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        if (reader.getQName().equalsLocalName(DOMAIN)) {
            this.domain = new Domain();
            this.domain.read(reader);
            reader.nextStartOrEndElement();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return MEANING_MOD;
    }

    @Override
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }

}
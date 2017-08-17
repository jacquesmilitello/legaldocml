package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.MeaningModType;
import io.legaldocml.akn.type.MeaningMods;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerEnum;
import static io.legaldocml.akn.util.XmlWriterHelper.writeMeaningModType;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element meaningMod is a metadata element specifying an (active or passive) modification in meaning for the
 * document.
 *
 * <pre>
 *   &lt;xsd:element name="meaningMod"&gt;
 * 	   &lt;xsd:complexType&gt;
 * 	     &lt;xsd:complexContent&gt;
 * 		   &lt;xsd:extension base="modificationType"&gt;
 * 		     &lt;xsd:sequence&gt;
 * 			   &lt;xsd:element ref="domain" minOccurs="0" maxOccurs="1"/&gt;
 * 			 &lt;xsd:sequence&gt;
 * 			 &lt;xsd:attributeGroup ref="meaningModType"/&gt;
 * 		   &lt;xsd:extension&gt;
 * 		 &lt;xsd:complexContent&gt;
 * 	   &lt;xsd:complexType&gt;
 *   &lt;xsd:element&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class MeaningMod extends ModificationType implements MeaningModType, AmendmentsElement {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "meaningMod";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    protected static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {

        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(ModificationType.ATTRIBUTES)
                .put(MeaningModType.ATTRIBUTE, biConsumerEnum(getFieldOffset(MeaningMod.class, "type"), MeaningMods.class))
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
        writer.writeStart(ADDRESS, 10);
        writeMeaningModType(writer, this);
        super.write(writer);
        if (this.domain != null) {
            this.domain.write(writer);
        }
        writer.writeEnd(ADDRESS, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        if (reader.getQName().equalsLocalName(Domain.ELEMENT)) {
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
        return ELEMENT;
    }

    @Override
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }
}
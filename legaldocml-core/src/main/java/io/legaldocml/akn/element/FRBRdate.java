package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.attribute.Date;
import io.legaldocml.akn.attribute.Name;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerDateTime;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.util.XmlWriterHelper.writeDate;
import static io.legaldocml.akn.util.XmlWriterHelper.writeName;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element FRBRdate is the metadata property containing a relevant date of the document in the respective level of
 * the FRBR hierarchy. Attribute name specifies which actual date is contained here.
 *
 * <pre>
 *   <xsd:element name="FRBRdate">
 *     <xsd:complexType>
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="metaopt">
 * 		     <xsd:attributeGroup ref="date"/>
 * 			 <xsd:attributeGroup ref="name"/>
 * 		   <xsd:extension>
 * 		 <xsd:complexContent>
 *     <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRdate extends MetaOpt implements Date, Name {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "FRBRdate";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(MetaOpt.ATTRIBUTES)
                .put(AknAttributes.DATE, biConsumerDateTime(getFieldOffset(FRBRdate.class, "date")))
                .put(AknAttributes.NAME, biConsumerString(getFieldOffset(FRBRdate.class, "name")))
                .build();
    }

    private OffsetDateTime date;
    private String name;

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    public OffsetDateTime getDate() {
        return this.date;
    }

    /**
     * {@inheritDoc}
     */
    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 8);
        writeDate(writer, this);
        writeName(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}
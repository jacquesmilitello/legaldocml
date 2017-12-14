package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Date;
import io.legaldocml.akn.attribute.Name;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.time.OffsetDateTime;

import static io.legaldocml.akn.AknAttributes.DATE;
import static io.legaldocml.akn.AknAttributes.NAME;
import static io.legaldocml.akn.AknElements.FRBR_DATE;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4DateTime;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.akn.util.XmlWriterHelper.writeDate;
import static io.legaldocml.akn.util.XmlWriterHelper.writeName;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element FRBRdate is the metadata property containing a relevant date of the document in the respective level of
 * the FRBR hierarchy. Attribute name specifies which actual date is contained here.
 * <p>
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
     * Memory address.
     */
    private static final long ADDRESS_FRBR_DATE = Buffers.address(FRBR_DATE);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(MetaOpt.ATTRIBUTES)
                .put(DATE, attributeGetterSetter4DateTime(DATE, getFieldOffset(FRBRdate.class, "date")))
                .put(NAME, attributeGetterSetter4String(NAME, getFieldOffset(FRBRdate.class, "name")))
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
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_FRBR_DATE, 8);
        writeDate(writer, this);
        writeName(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_FRBR_DATE, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return FRBR_DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        visitor.visit(this);
    }

}
package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Date;
import io.legaldocml.akn.attribute.Name;
import io.legaldocml.akn.attribute.Number;
import io.legaldocml.akn.attribute.RefersOpt;
import io.legaldocml.akn.attribute.ShowReq;
import io.legaldocml.akn.type.ListReferenceRef;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerDateTime;
import static io.legaldocml.akn.element.Attributes.biConsumerListReferenceRef;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.util.XmlWriterHelper.writeDate;
import static io.legaldocml.akn.util.XmlWriterHelper.writeName;
import static io.legaldocml.akn.util.XmlWriterHelper.writeNumber;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRefers;
import static io.legaldocml.akn.util.XmlWriterHelper.writeShow;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element publication is the metadata container specifying an official publication event for the FRBR expression of
 * the document.
 *
 * <pre>
 *   <xsd:element name="publication">
 * 	   <xsd:complexType>
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="metaopt">
 * 		     <xsd:attributeGroup ref="date"/>
 * 			 <xsd:attributeGroup ref="show"/>
 * 			 <xsd:attributeGroup ref="name"/>
 * 			 <xsd:attributeGroup ref="number"/>
 * 			 <xsd:attributeGroup ref="refers"/>
 * 		   <xsd:extension>
 * 	     <xsd:complexContent>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Publication extends MetaOpt implements Date, ShowReq, Name, Number, RefersOpt {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT_PUBLICATION = "publication";

    /**
     * Memory address.
     */
    private static final long ADDRESS_PUBLICATION = Buffers.address(ELEMENT_PUBLICATION);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(MetaOpt.ATTRIBUTES)
                .put(Date.ATTRIBUTE, biConsumerDateTime(getFieldOffset(Publication.class, "date")))
                .put(ShowReq.ATTRIBUTE_SHOW_AS, biConsumerString(getFieldOffset(Publication.class, "showAs")))
                .put(ShowReq.ATTRIBUTE_SHORT_FORM, biConsumerString(getFieldOffset(Publication.class, "shortForm")))
                .put(Name.ATTRIBUTE, biConsumerString(getFieldOffset(Publication.class, "name")))
                .put(Number.ATTRIBUTE, biConsumerString(getFieldOffset(Publication.class, "number")))
                .put(RefersOpt.ATTRIBUTE, biConsumerListReferenceRef(getFieldOffset(Publication.class, "refersTo")))
                .build();
    }

    // Mandatory
    private OffsetDateTime date;

    // Mandatory
    private String showAs;

    // Optional
    private String shortForm;
    private String name;
    private String number;
    private ListReferenceRef refersTo;

    /**
     * {@inheritDoc}
     */
    @Override
    public ListReferenceRef getRefersTo() {
        return this.refersTo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRefersTo(ListReferenceRef refersTo) {
        this.refersTo = refersTo;
    }

    /**
     * {@inheritDoc}
     */
    public String getNumber() {
        return this.number;
    }

    /**
     * {@inheritDoc}
     */
    public void setNumber(String number) {
        this.number = number;
    }

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
    public String getShowAs() {
        return this.showAs;
    }

    /**
     * {@inheritDoc}
     */
    public void setShowAs(String showAs) {
        this.showAs = showAs;
    }

    /**
     * {@inheritDoc}
     */
    public String getShortForm() {
        return this.shortForm;
    }

    /**
     * {@inheritDoc}
     */
    public void setShortForm(String shortForm) {
        this.shortForm = shortForm;
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
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_PUBLICATION, 11);
        writeDate(writer, this);
        writeName(writer, this);
        writeRefers(writer, this);
        writeShow(writer, this);
        writeNumber(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_PUBLICATION, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT_PUBLICATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }
}
package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Date;
import io.legaldocml.akn.attribute.Name;
import io.legaldocml.akn.attribute.Number;
import io.legaldocml.akn.attribute.RefersOpt;
import io.legaldocml.akn.attribute.ShowReq;
import io.legaldocml.akn.type.ListReferenceRef;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;
import java.time.temporal.Temporal;

import static io.legaldocml.akn.AknAttributes.DATE;
import static io.legaldocml.akn.AknAttributes.NAME;
import static io.legaldocml.akn.AknAttributes.NUMBER;
import static io.legaldocml.akn.AknAttributes.REFERS_TO;
import static io.legaldocml.akn.AknAttributes.SHORT_FORM;
import static io.legaldocml.akn.AknAttributes.SHOW_AS;
import static io.legaldocml.akn.AknElements.PUBLICATION;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4DateTime;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4ListReferenceRef;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.akn.util.XmlWriterHelper.writeDate;
import static io.legaldocml.akn.util.XmlWriterHelper.writeNumber;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRefers;
import static io.legaldocml.akn.util.XmlWriterHelper.writeShow;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element publication is the metadata container specifying an official publication event for the FRBR expression of
 * the document.
 * <p>
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
     * Memory address.
     */
    private static final long ADDRESS_PUBLICATION = Buffers.address(PUBLICATION);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(MetaOpt.ATTRIBUTES)
                .put(DATE, attributeGetterSetter4DateTime(DATE, getFieldOffset(Publication.class, "date")))
                .put(SHOW_AS, attributeGetterSetter4String(SHOW_AS, getFieldOffset(Publication.class, "showAs")))
                .put(SHORT_FORM, attributeGetterSetter4String(SHORT_FORM, getFieldOffset(Publication.class, "shortForm")))
                .put(NAME, attributeGetterSetter4String(NAME, getFieldOffset(Publication.class, "name")))
                .put(NUMBER, attributeGetterSetter4String(NUMBER, getFieldOffset(Publication.class, "number")))
                .put(REFERS_TO, attributeGetterSetter4ListReferenceRef(REFERS_TO, getFieldOffset(Publication.class, "refersTo")))
                .build();
    }

    // Mandatory
    private Temporal date;

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
    public Temporal getDate() {
        return this.date;
    }

    /**
     * {@inheritDoc}
     */
    public void setDate(Temporal date) {
        this.date = date;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_PUBLICATION, 11);
        writeDate(writer, this);
        Name.super.write(writer);
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
        return PUBLICATION;
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
    public void accept(AknVisitor visitor) {
        visitor.visit(this);
    }
}
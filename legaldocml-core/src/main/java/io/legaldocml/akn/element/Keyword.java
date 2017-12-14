package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Dictionary;
import io.legaldocml.akn.attribute.LinkOpt;
import io.legaldocml.akn.attribute.RefersOpt;
import io.legaldocml.akn.attribute.ShowReq;
import io.legaldocml.akn.attribute.ValueReq;
import io.legaldocml.akn.type.ListReferenceRef;
import io.legaldocml.akn.type.ReferenceRef;
import io.legaldocml.akn.type.Uri;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.DICTIONARY;
import static io.legaldocml.akn.AknAttributes.HREF;
import static io.legaldocml.akn.AknAttributes.REFERS_TO;
import static io.legaldocml.akn.AknAttributes.SHORT_FORM;
import static io.legaldocml.akn.AknAttributes.SHOW_AS;
import static io.legaldocml.akn.AknAttributes.VALUE;
import static io.legaldocml.akn.AknElements.KEYWORD;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4ReferenceRef;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.akn.util.XmlWriterHelper.writeDictionary;
import static io.legaldocml.akn.util.XmlWriterHelper.writeLinkOpt;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRefers;
import static io.legaldocml.akn.util.XmlWriterHelper.writeShow;
import static io.legaldocml.akn.util.XmlWriterHelper.writeValue;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element keyword is a metadata element specifying a keyword  associated to the FRBR expression of the document.
 * Attribute dictionary (required) specifies the thesaurus out of which the keyword has been taken. Attribute href
 * points to the fragment of text this keyword is associated to. Keywords without href attribute refer to the content as
 * a whole.
 *
 * <pre>
 *   <xsd:element name="keyword">
 * 	   <xsd:complexType>
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="metaopt">
 * 		     <xsd:attributeGroup ref="linkopt"/>
 *           <xsd:attributeGroup ref="value"/>
 *           <xsd:attributeGroup ref="show"/>
 *           <xsd:attributeGroup ref="refers"/>
 *           <xsd:attributeGroup ref="dictionary"/>
 * 		   </xsd:extension>
 * 	     </xsd:complexContent>
 * 	   </xsd:complexType>
 *   </xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Keyword extends MetaOpt implements LinkOpt, ValueReq, ShowReq, RefersOpt, Dictionary {

    /**
     * Memory address.
     */
    private static final long ADDRESS_KEYWORD = Buffers.address(KEYWORD);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(MetaOpt.ATTRIBUTES)
                .put(SHOW_AS, attributeGetterSetter4String(SHOW_AS, getFieldOffset(Keyword.class, "showAs")))
                .put(SHORT_FORM, attributeGetterSetter4String(SHORT_FORM, getFieldOffset(Keyword.class, "shortForm")))
                .put(VALUE, attributeGetterSetter4String(VALUE, getFieldOffset(Keyword.class, "value")))
                .put(DICTIONARY, attributeGetterSetter4ReferenceRef(DICTIONARY, getFieldOffset(Keyword.class, "dictionary")))
                .put(HREF, attributeGetterSetter4String(HREF, getFieldOffset(Keyword.class, "href")))
                .put(REFERS_TO, attributeGetterSetter4String(REFERS_TO, getFieldOffset(Keyword.class, "refersTo")))
                .build();
    }

    private String showAs;
    private String shortForm;
    private String value;
    private ReferenceRef dictionary;
    private ListReferenceRef refersTo;
    private Uri href;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getShortForm() {
        return this.shortForm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setShortForm(String shortForm) {
        this.shortForm = shortForm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getShowAs() {
        return this.showAs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setShowAs(String showAs) {
        this.showAs = showAs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getValue() {
        return this.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDictionary(ReferenceRef dictionary) {
        this.dictionary = dictionary;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReferenceRef getDictionary() {
        return this.dictionary;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Uri getHref() {
        return this.href;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHref(Uri href) {
        this.href = href;
    }

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
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_KEYWORD, 7);
        super.write(writer);
        writeShow(writer, this);
        writeValue(writer, this);
        writeDictionary(writer, this);
        writeLinkOpt(writer, this);
        writeRefers(writer, this);
        writer.writeEnd(ADDRESS_KEYWORD, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return KEYWORD;
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
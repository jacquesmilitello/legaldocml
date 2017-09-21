package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.attribute.Dictionary;
import io.legaldocml.akn.attribute.LinkOpt;
import io.legaldocml.akn.attribute.RefersOpt;
import io.legaldocml.akn.attribute.ShowReq;
import io.legaldocml.akn.attribute.ValueReq;
import io.legaldocml.akn.type.ListReferenceRef;
import io.legaldocml.akn.type.ReferenceRef;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.util.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.util.Uri;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.AknElements.KEYWORD;
import static io.legaldocml.akn.element.Attributes.biConsumerReferenceRef;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
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

    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(MetaOpt.ATTRIBUTES)
                .put(AknAttributes.SHOW_AS, biConsumerString(getFieldOffset(Keyword.class, "showAs")))
                .put(AknAttributes.SHORT_FORM, biConsumerString(getFieldOffset(Keyword.class, "shortForm")))
                .put(AknAttributes.VALUE, biConsumerString(getFieldOffset(Keyword.class, "value")))
                .put(AknAttributes.DICTIONARY, biConsumerReferenceRef(getFieldOffset(Keyword.class, "dictionary")))
                .put(AknAttributes.HREF, biConsumerString(getFieldOffset(Keyword.class, "href")))
                .put(AknAttributes.REFERS_TO, biConsumerString(getFieldOffset(Keyword.class, "refersTo")))
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
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
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
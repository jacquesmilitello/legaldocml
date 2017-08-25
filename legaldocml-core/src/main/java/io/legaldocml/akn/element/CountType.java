package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Core;
import io.legaldocml.akn.attribute.LinkOpt;
import io.legaldocml.akn.attribute.RefersOpt;
import io.legaldocml.akn.attribute.ValueReq;
import io.legaldocml.akn.type.ListReferenceRef;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.io.Attribute;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.CharArrays;
import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.module.Module;
import io.legaldocml.module.Modules;
import io.legaldocml.util.Uri;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerListReferenceRef;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.element.Attributes.biConsumerUri;
import static io.legaldocml.akn.util.XmlWriterHelper.writeLinkOpt;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRefers;
import static io.legaldocml.akn.util.XmlWriterHelper.writeValue;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The complex type countType lists all the properties associated to elements of parliamentary count.
 *
 * <pre>
 *   <xsd:complexType name="countType">
 * 	   <xsd:choice>
 * 	     <xsd:any namespace="##other" processContents="lax" minOccurs="0" maxOccurs="unbounded"/>
 * 	   <xsd:choice>
 * 	   <xsd:attributeGroup ref="core"/>
 * 	   <xsd:attributeGroup ref="idreq"/>
 * 	   <xsd:attributeGroup ref="value"/>
 * 	   <xsd:attributeGroup ref="refers"/>
 * 	   <xsd:attributeGroup ref="linkopt"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class CountType extends IdReqImpl implements Core, ValueReq, RefersOpt, LinkOpt {

    protected static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(IdReqImpl.ATTRIBUTES)
                .put(RefersOpt.ATTRIBUTE, biConsumerListReferenceRef(getFieldOffset(CountType.class, "refersTo")))
                .put(AknAttributes.HREF, biConsumerUri(getFieldOffset(CountType.class, "href")))
                .put(AknAttributes.VALUE, biConsumerString(getFieldOffset(CountType.class, "value")))
                .build();
    }

    // Mandatory
    private String value;
    // Optional
    private ListReferenceRef refersTo;
    // Optional
    private Uri href;

    private java.util.List<Attribute> attributes;

    private AknList<CountTypeElement> others;

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
    public void add(Attribute attribute) {
        if (this.attributes == null) {
            this.attributes = new ArrayList<>();
        }
        this.attributes.add(attribute);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        QName qName = reader.getQName();
        super.read(reader);
        reader.nextStartOrEndElement();

        if (qName.equals(reader.getQName())) {
            // ok end of parent tag -> to extension -> return;
            return;
        }

        this.others = new AknList<>(new CountTypeElement[4]);

        while (!qName.equals(reader.getQName())) {
            CharArray array = reader.getNamespaces().get(CharArrays.constant(reader.getQName().getPrefix()));
            Module module = Modules.get(array);
            CountTypeElement element = module.element(reader.getQName().getLocalName(), CountTypeElement.class).get();
            element.read(reader);
            this.others.add(element);
            reader.nextStartOrEndElement();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        super.write(writer);
        writeRefers(writer, this);
        writeLinkOpt(writer, this);
        writeValue(writer, this);
        if (this.attributes != null) {
            for (int i = 0, n = this.attributes.size(); i < n; i++) {
                this.attributes.get(i).write(writer);
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }
}
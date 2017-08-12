package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Core;
import io.legaldocml.akn.attribute.LinkOpt;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.io.Attribute;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.CharArrays;
import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.module.Module;
import io.legaldocml.util.Uri;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.ADDRESS_HREF;
import static io.legaldocml.akn.element.Attributes.biConsumerUri;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The complex type anyOtherType defines an open content model for elements that may use elements from other namespaces.
 *
 * <pre>
 *   &lt;xsd:complexType name="anyOtherType"&gt;
 * 	   &lt;xsd:choice&gt;
 * 	     &lt;xsd:any namespace="##other" processContents="lax" minOccurs="0" maxOccurs="unbounded"/&gt;
 * 	   &lt;xsd:choice&gt;
 * 	   &lt;xsd:attributeGroup ref="core"/&gt;
 * 	   &lt;xsd:attributeGroup ref="idopt"/&gt;
 * 	   &lt;xsd:attributeGroup ref="linkopt"/&gt;
 *   &lt;xsd:complexType&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class AnyOtherType extends IdOptImpl implements LinkOpt, Core {

    protected static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(IdOptImpl.ATTRIBUTES)
                .put(LinkOpt.ATTRIBUTE, biConsumerUri(getFieldOffset(AnyOtherType.class, "href")))
                .build();
    }

    private Uri href;

    private AknList<AnyOtherTypeElement> others;

    private java.util.List<Attribute> attributes;


    /**
     * {@inheritDoc}
     */
    @Override
    public final Uri getHref() {
        return this.href;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setHref(Uri href) {
        this.href = href;
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
    public void write(XmlWriter writer) throws IOException {
        if (this.attributes != null) {
            for (int i = 0, n = this.attributes.size(); i < n; i++) {
                this.attributes.get(i).write(writer);
            }
        }
        if (this.href != null) {
            writer.writeAttribute(ADDRESS_HREF, 4, this.href.getChars());
        }
        super.write(writer);
        if (this.others != null) {
            this.others.write(writer);
        }
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

        this.others = new AknList<>(new AnyOtherTypeElement[4]);

        while (!qName.equals(reader.getQName())) {
            CharArray array = reader.getNamespaces().get(CharArrays.constant(reader.getQName().getPrefix()));


            Module module = reader.getContext().getModule(array);
            AnyOtherTypeElement element = module.element(reader.getQName().getLocalName(), AnyOtherTypeElement.class).get();
            element.read(reader);
            this.others.add(element);
            reader.nextStartOrEndElement();
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
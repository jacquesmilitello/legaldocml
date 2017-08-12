package io.legaldocml.akn.element;


import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Core;
import io.legaldocml.akn.attribute.LinkReq;
import io.legaldocml.akn.attribute.Name;
import io.legaldocml.akn.attribute.ShowReq;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlWriterHelper;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Uri;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.element.Attributes.biConsumerUri;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * <pre>
 *   &lt;xsd:element name="componentData"&gt;
 * 	   &lt;xsd:complexType&gt;
 * 	     &lt;xsd:sequence minOccurs="0" maxOccurs="unbounded"&gt;
 * 		   &lt;xsd:element ref="componentData"/&gt;
 * 		 &lt;xsd:sequence&gt;
 * 	     &lt;xsd:attributeGroup ref="core"/&gt;
 * 		 &lt;xsd:attributeGroup ref="idreq"/&gt;
 * 		 &lt;xsd:attributeGroup ref="name"/&gt;
 * 	 	 &lt;xsd:attributeGroup ref="link"/&gt;
 * 		 &lt;xsd:attributeGroup ref="show"/&gt;
 * 	   &lt;xsd:complexType&gt;
 *   &lt;xsd:element&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ComponentData extends IdReqImpl implements Name, LinkReq, ShowReq, Core {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "componentData";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(IdReqImpl.ATTRIBUTES)
                .put(LinkReq.ATTRIBUTE, biConsumerUri(getFieldOffset(ComponentData.class, "href")))
                .put(Name.ATTRIBUTE, biConsumerString(getFieldOffset(ComponentData.class, "name")))
                .put(ShowReq.ATTRIBUTE_SHOW_AS, biConsumerString(getFieldOffset(ComponentData.class, "showAs")))
                .put(ShowReq.ATTRIBUTE_SHORT_FORM, biConsumerString(getFieldOffset(ComponentData.class, "shortForm")))
                .build();
    }


    private AknList<ComponentData> componentData;
    private Uri href;
    private String name;
    private String showAs;
    private String shortForm;

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
    public Uri getHref() {
        return this.href;
    }

    /**
     * {@inheritDoc}
     */
    public void setHref(Uri href) {
        this.href = href;
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
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 13);
        XmlWriterHelper.writeLinkReq(writer, this);
        XmlWriterHelper.writeName(writer, this);
        XmlWriterHelper.writeShow(writer, this);
        super.write(writer);
        if (this.componentData != null) {
            this.componentData.write(writer);
        }
        writer.writeEnd(ADDRESS, 13);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        reader.nextStartOrEndElement();
    }

    /**
     * {@inheritDoc}
     */
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}
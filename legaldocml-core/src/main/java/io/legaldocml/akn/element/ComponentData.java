package io.legaldocml.akn.element;


import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.attribute.Core;
import io.legaldocml.akn.attribute.IdReq;
import io.legaldocml.akn.attribute.LinkReq;
import io.legaldocml.akn.attribute.Name;
import io.legaldocml.akn.attribute.ShowReq;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.util.CharArray;
import io.legaldocml.akn.type.Uri;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.AknElements.COMPONENT_DATA;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.element.Attributes.biConsumerUri;
import static io.legaldocml.akn.util.XmlWriterHelper.writeLinkReq;
import static io.legaldocml.akn.util.XmlWriterHelper.writeName;
import static io.legaldocml.akn.util.XmlWriterHelper.writeShow;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * <pre>
 *   <xsd:element name="componentData">
 * 	   <xsd:complexType>
 * 	     <xsd:sequence minOccurs="0" maxOccurs="unbounded">
 * 		   <xsd:element ref="componentData"/>
 * 		 <xsd:sequence>
 * 	     <xsd:attributeGroup ref="core"/>
 * 		 <xsd:attributeGroup ref="idreq"/>
 * 		 <xsd:attributeGroup ref="name"/>
 * 	 	 <xsd:attributeGroup ref="link"/>
 * 		 <xsd:attributeGroup ref="show"/>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ComponentData extends AbstractId implements IdReq, Name, LinkReq, ShowReq, Core {

    /**
     * Memory address.
     */
    private static final long ADDRESS_COMPONENT_DATA = Buffers.address(COMPONENT_DATA);

    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(AbstractId.ATTRIBUTES)
                .put(AknAttributes.HREF, biConsumerUri(getFieldOffset(ComponentData.class, "href")))
                .put(AknAttributes.NAME, biConsumerString(getFieldOffset(ComponentData.class, "name")))
                .put(AknAttributes.SHOW_AS, biConsumerString(getFieldOffset(ComponentData.class, "showAs")))
                .put(AknAttributes.SHORT_FORM, biConsumerString(getFieldOffset(ComponentData.class, "shortForm")))
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
        writer.writeStart(ADDRESS_COMPONENT_DATA, 13);
        IdReq.super.write(writer);
        writeLinkReq(writer, this);
        writeName(writer, this);
        writeShow(writer, this);
        if (this.componentData != null) {
            this.componentData.write(writer);
        }
        writer.writeEnd(ADDRESS_COMPONENT_DATA, 13);
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
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return COMPONENT_DATA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            if (this.componentData != null) {
                this.componentData.accept(visitor);
            }
            visitor.visitLeave(this);
        }
    }

}
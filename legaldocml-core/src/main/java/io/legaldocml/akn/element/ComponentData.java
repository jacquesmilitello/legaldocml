package io.legaldocml.akn.element;


import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Core;
import io.legaldocml.akn.attribute.IdReq;
import io.legaldocml.akn.attribute.LinkReq;
import io.legaldocml.akn.attribute.Name;
import io.legaldocml.akn.attribute.ShowReq;
import io.legaldocml.akn.type.Uri;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.HREF;
import static io.legaldocml.akn.AknAttributes.NAME;
import static io.legaldocml.akn.AknAttributes.SHORT_FORM;
import static io.legaldocml.akn.AknAttributes.SHOW_AS;
import static io.legaldocml.akn.AknElements.COMPONENT_DATA;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4Uri;
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

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(AbstractId.ATTRIBUTES)
                .put(HREF, attributeGetterSetter4Uri(HREF, getFieldOffset(ComponentData.class, "href")))
                .put(NAME, attributeGetterSetter4String(NAME, getFieldOffset(ComponentData.class, "name")))
                .put(SHOW_AS, attributeGetterSetter4String(SHOW_AS, getFieldOffset(ComponentData.class, "showAs")))
                .put(SHORT_FORM, attributeGetterSetter4String(SHORT_FORM, getFieldOffset(ComponentData.class, "shortForm")))
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
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
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
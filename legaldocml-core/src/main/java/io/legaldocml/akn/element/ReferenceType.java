package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Core;
import io.legaldocml.akn.attribute.IdReq;
import io.legaldocml.akn.attribute.LinkReq;
import io.legaldocml.akn.attribute.ShowReq;
import io.legaldocml.akn.type.Uri;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.HREF;
import static io.legaldocml.akn.AknAttributes.SHORT_FORM;
import static io.legaldocml.akn.AknAttributes.SHOW_AS;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4Uri;
import static io.legaldocml.akn.util.XmlWriterHelper.writeShow;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The complex type referenceType defines the empty content model and the list of attributes for metadata elements in
 * the references section.
 * <p>
 * <pre>
 *   <xsd:complexType name="referenceType">
 * 	   <xsd:attributeGroup ref="core"/>
 * 	   <xsd:attributeGroup ref="idreq"/>
 * 	   <xsd:attributeGroup ref="link"/>
 * 	   <xsd:attributeGroup ref="show"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class ReferenceType extends AbstractIdCore implements IdReq, LinkReq, ShowReq, Core {

    protected static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(MetaOpt.ATTRIBUTES)
                .put(SHOW_AS, attributeGetterSetter4String(SHOW_AS, getFieldOffset(ReferenceType.class, "showAs")))
                .put(SHORT_FORM, attributeGetterSetter4String(SHORT_FORM, getFieldOffset(ReferenceType.class, "shortForm")))
                .put(HREF, attributeGetterSetter4Uri(HREF, getFieldOffset(ReferenceType.class, "href")))
                .build();
    }

    // Mandatory
    private Uri href;
    // Mandatory
    private String showAs;
    // Optional
    private String shortForm;

    /**
     * {@inheritDoc}
     */
    public final String getShowAs() {
        return this.showAs;
    }

    /**
     * {@inheritDoc}
     */
    public final void setShowAs(String showAs) {
        this.showAs = showAs;
    }

    /**
     * {@inheritDoc}
     */
    public final String getShortForm() {
        return this.shortForm;
    }

    /**
     * {@inheritDoc}
     */
    public final void setShortForm(String shortForm) {
        this.shortForm = shortForm;
    }

    /**
     * {@inheritDoc}
     */
    public final Uri getHref() {
        return this.href;
    }

    /**
     * {@inheritDoc}
     */
    public final void setHref(Uri href) {
        this.href = href;
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
        IdReq.super.write(writer);
        LinkReq.super.write(writer);
        writeShow(writer, this);
    }

}
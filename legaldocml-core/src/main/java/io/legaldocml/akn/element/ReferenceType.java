package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.attribute.Core;
import io.legaldocml.akn.attribute.IdReq;
import io.legaldocml.akn.attribute.LinkReq;
import io.legaldocml.akn.attribute.ShowReq;
import io.legaldocml.util.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.akn.type.Uri;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.element.Attributes.biConsumerUri;
import static io.legaldocml.akn.util.XmlWriterHelper.writeLinkReq;
import static io.legaldocml.akn.util.XmlWriterHelper.writeShow;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The complex type referenceType defines the empty content model and the list of attributes for metadata elements in
 * the references section.
 *
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
public abstract class ReferenceType extends AbstractId implements IdReq, LinkReq, ShowReq, Core {

    protected static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(MetaOpt.ATTRIBUTES)
                .put(AknAttributes.SHOW_AS, biConsumerString(getFieldOffset(ReferenceType.class, "showAs")))
                .put(AknAttributes.SHORT_FORM, biConsumerString(getFieldOffset(ReferenceType.class, "shortForm")))
                .put(AknAttributes.HREF, biConsumerUri(getFieldOffset(ReferenceType.class, "href")))
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
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        IdReq.super.write(writer);
        writeLinkReq(writer, this);
        writeShow(writer, this);
    }

}
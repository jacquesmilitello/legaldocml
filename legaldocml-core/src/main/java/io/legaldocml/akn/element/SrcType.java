package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Core;
import io.legaldocml.akn.attribute.IdOpt;
import io.legaldocml.akn.attribute.ShowReq;
import io.legaldocml.akn.attribute.Src;
import io.legaldocml.akn.type.ManifestationURI;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.ALT;
import static io.legaldocml.akn.AknAttributes.SHORT_FORM;
import static io.legaldocml.akn.AknAttributes.SHOW_AS;
import static io.legaldocml.akn.AknAttributes.SRC;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4ManifestationURI;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.akn.util.XmlWriterHelper.writeShow;
import static io.legaldocml.akn.util.XmlWriterHelper.writeSrc;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The complex type srcType defines the empty content model and the list of attributes for manifestation-level
 * references to external resources.
 * <p>
 * <pre>
 *   <xsd:complexType name="srcType">
 * 	   <xsd:attributeGroup ref="core"/>
 * 	   <xsd:attributeGroup ref="idopt"/>
 * 	   <xsd:attributeGroup ref="src"/>
 * 	   <xsd:attributeGroup ref="show"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class SrcType extends AbstractId implements Core, IdOpt, ShowReq, Src {

    protected static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(MetaOpt.ATTRIBUTES)
                .put(SRC, attributeGetterSetter4ManifestationURI(SRC, getFieldOffset(SrcType.class, "src")))
                .put(ALT, attributeGetterSetter4String(ALT, getFieldOffset(SrcType.class, "alt")))
                .put(SHOW_AS, attributeGetterSetter4String(SHOW_AS, getFieldOffset(SrcType.class, "showAs")))
                .put(SHORT_FORM, attributeGetterSetter4String(SHORT_FORM, getFieldOffset(SrcType.class, "shortForm")))
                .build();
    }

    private ManifestationURI src;
    private String alt;
    private String showAs;
    private String shortForm;

    /**
     * {@inheritDoc}
     */
    @Override
    public ManifestationURI getSrc() {
        return this.src;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSrc(ManifestationURI src) {
        this.src = src;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAlt() {
        return this.alt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAlt(String alt) {
        this.alt = alt;
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
    public void write(XmlWriter writer) throws IOException {
        IdOpt.super.write(writer);
        Core.super.write(writer);
        writeSrc(writer, this);
        writeShow(writer, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        reader.nextStartOrEndElement();
    }

    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }
}
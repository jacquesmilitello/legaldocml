package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.ImgAtts;
import io.legaldocml.akn.attribute.Src;
import io.legaldocml.akn.group.HTMLMarker;
import io.legaldocml.akn.type.ManifestationURI;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.ALT;
import static io.legaldocml.akn.AknAttributes.HEIGHT;
import static io.legaldocml.akn.AknAttributes.SRC;
import static io.legaldocml.akn.AknAttributes.WIDTH;
import static io.legaldocml.akn.AknElements.IMG;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4Integer;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4ManifestationURI;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.akn.util.XmlWriterHelper.writeSrc;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;
import static io.legaldocml.unsafe.UnsafeString.getChars;

/**
 * The element img is an HTML element and is used in Akoma Ntoso as in HTML, for including an image. It is a marker.
 * <p>
 * <pre>
 *   <xsd:element name="img">
 * 	   <xsd:complexType>
 * 	     <xsd:complexContent>
 *  	   <xsd:extension base="markeropt">
 * 		     <xsd:attributeGroup ref="src"/>
 * 			 <xsd:attributeGroup ref="imgAtts"/>
 * 	 	   <xsd:extension>
 * 		 <xsd:complexContent>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Img extends MarkerOpt implements Src, ImgAtts, HTMLMarker {

    /**
     * Memory address.
     */
    private static final long ADDRESS_IMG = Buffers.address(IMG);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(Blocksopt.ATTRIBUTES)
                .put(ALT, attributeGetterSetter4String(ALT, getFieldOffset(Img.class, "alt")))
                .put(SRC, attributeGetterSetter4ManifestationURI(SRC, getFieldOffset(Img.class, "src")))
                .put(WIDTH, attributeGetterSetter4Integer(WIDTH, getFieldOffset(Img.class, "width")))
                .put(HEIGHT, attributeGetterSetter4Integer(HEIGHT, getFieldOffset(Img.class, "height")))
                .build();
    }

    private ManifestationURI src;
    private String alt;
    private Integer width;
    private Integer height;

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
    public Integer getWidth() {
        return this.width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getHeight() {
        return this.height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_IMG, 3);
        writeSrc(writer, this);
        if (this.width != null) {
            writer.writeAttribute(Attributes.ADDRESS_WIDTH, 5, getChars(this.width.toString()));
        }
        if (this.height != null) {
            writer.writeAttribute(Attributes.ADDRESS_HEIGHT, 5, getChars(this.height.toString()));
        }
        super.write(writer);
        writer.writeEnd(ADDRESS_IMG, 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return IMG;
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
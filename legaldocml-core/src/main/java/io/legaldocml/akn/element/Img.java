package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.ImgAtts;
import io.legaldocml.akn.attribute.Src;
import io.legaldocml.akn.group.HTMLMarker;
import io.legaldocml.akn.type.ManifestationURI;
import io.legaldocml.akn.util.XmlWriterHelper;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerInteger;
import static io.legaldocml.akn.element.Attributes.biConsumerManifestationURI;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;
import static io.legaldocml.unsafe.UnsafeString.getChars;

/**
 * The element img is an HTML element and is used in Akoma Ntoso as in HTML, for including an image. It is a marker.
 * <p/>
 * <pre>
 *   <xsd:element name="img">
 * 	   <xsd:complexType>
 * 	     <xsd:complexContent>
 *  	   <xsd:extension base="markeropt">
 * 		     <xsd:attributeGroup ref="src"/>
 * 			 <xsd:attributeGroup ref="imgAtts"/>
 * 	 	   </xsd:extension>
 * 		 </xsd:complexContent>
 * 	   </xsd:complexType>
 *   </xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Img extends MarkerOpt implements Src, ImgAtts, HTMLMarker {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "img";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(Blocksopt.ATTRIBUTES)
                .put(Src.ATTRIBUTE_ALT, biConsumerString(getFieldOffset(Img.class, "alt")))
                .put(Src.ATTRIBUTE_SRC, biConsumerManifestationURI(Src.ATTRIBUTE_SRC, getFieldOffset(Img.class, "src")))
                .put(ImgAtts.ATTRIBUTE_WIDTH, biConsumerInteger(getFieldOffset(Img.class, "width")))
                .put(ImgAtts.ATTRIBUTE_HEIGHT, biConsumerInteger(getFieldOffset(Img.class, "height")))
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
        writer.writeStart(ADDRESS, 3);
        XmlWriterHelper.writeSrc(writer, this);
        if (this.width != null) {
            writer.writeAttribute(Attributes.ADDRESS_WIDTH, 5, getChars(this.width.toString()));
        }
        if (this.height != null) {
            writer.writeAttribute(Attributes.ADDRESS_HEIGHT, 5, getChars(this.height.toString()));
        }
        super.write(writer);
        writer.writeEnd(ADDRESS, 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }
}
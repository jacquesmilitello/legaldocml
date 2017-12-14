package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Level;
import io.legaldocml.akn.attribute.LinkReq;
import io.legaldocml.akn.type.Uri;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.HREF;
import static io.legaldocml.akn.AknAttributes.LEVEL;
import static io.legaldocml.akn.AknElements.TOC_ITEM;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4Uri;
import static io.legaldocml.akn.util.XmlWriterHelper.writeLevel;
import static io.legaldocml.akn.util.XmlWriterHelper.writeLinkReq;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element tocItem is a component of the table of content and contains header information about sections or parts of
 * the rest of the document.
 * <p>
 * <pre>
 *   <xsd:element name="tocItem">
 * 	   <xsd:complexType mixed="true">
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="inline">
 * 		     <xsd:attributeGroup ref="link"/>
 * 			 <xsd:attributeGroup ref="level"/>
 * 		   <xsd:extension>
 * 	     <xsd:complexContent>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class TocItem extends InlineType implements LinkReq, Level {

    /**
     * Memory address.
     */
    private static final long ADDRESS_TOC_ITEM = Buffers.address(TOC_ITEM);


    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;


    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(InlineType.ATTRIBUTES)
                .put(HREF, attributeGetterSetter4Uri(HREF, getFieldOffset(TocItem.class, "href")))
                .put(LEVEL, attributeGetterSetter4String(LEVEL, getFieldOffset(TocItem.class, "level")))
                .build();
    }

    // Mandatory
    private Uri href;

    // Mandatory
    private String level;

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
    public String getLevel() {
        return this.level;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_TOC_ITEM, 7);
        writeLinkReq(writer, this);
        writeLevel(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_TOC_ITEM, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return TOC_ITEM;
    }

    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }
}
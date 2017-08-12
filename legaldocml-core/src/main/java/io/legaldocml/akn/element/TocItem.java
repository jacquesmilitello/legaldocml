package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Level;
import io.legaldocml.akn.attribute.LinkReq;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Uri;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.element.Attributes.biConsumerUri;
import static io.legaldocml.akn.util.XmlWriterHelper.writeLevel;
import static io.legaldocml.akn.util.XmlWriterHelper.writeLinkReq;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element tocItem is a component of the table of content and contains header information about sections or parts of
 * the rest of the document.
 *
 * <pre>
 *   &lt;xsd:element name="tocItem"&gt;
 * 	   &lt;xsd:complexType mixed="true"&gt;
 * 	     &lt;xsd:complexContent&gt;
 * 		   &lt;xsd:extension base="inline"&gt;
 * 		     &lt;xsd:attributeGroup ref="link"/&gt;
 * 			 &lt;xsd:attributeGroup ref="level"/&gt;
 * 		   &lt;xsd:extension&gt;
 * 	     &lt;xsd:complexContent&gt;
 * 	   &lt;xsd:complexType&gt;
 *   &lt;xsd:element&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class TocItem extends InlineType implements LinkReq, Level {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "tocItem";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);


    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;


    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(InlineType.ATTRIBUTES)
                .put(LinkReq.ATTRIBUTE, biConsumerUri(getFieldOffset(TocItem.class, "href")))
                .put(Level.ATTRIBUTE, biConsumerString(getFieldOffset(TocItem.class, "level")))
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
        writer.writeStart(ADDRESS, 7);
        writeLinkReq(writer, this);
        writeLevel(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

    @Override
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }
}
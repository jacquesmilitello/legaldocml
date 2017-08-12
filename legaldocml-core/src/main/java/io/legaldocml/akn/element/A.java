package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.LinkReq;
import io.legaldocml.akn.attribute.Target;
import io.legaldocml.akn.group.HTMLinline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Uri;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.element.Attributes.biConsumerUri;
import static io.legaldocml.akn.util.XmlWriterHelper.writeLinkReq;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;
import static io.legaldocml.unsafe.UnsafeString.getChars;

/**
 * The element a is an HTML element and is used in Akoma Ntoso as in HTML, for the generic link to a web resource (NOT
 * to an Akoma Ntoso document: use ref for that). It is an inline.
 * <pre>
 *   &lt;xsd:element name="a"&gt;
 * 	   &lt;xsd:complexType mixed="true"&gt;
 * 	     &lt;xsd:complexContent&gt;
 * 		   &lt;xsd:extension base="inline"&gt;
 * 		     &lt;xsd:attributeGroup ref="link"/&gt;
 * 			 &lt;xsd:attributeGroup ref="target"/&gt;
 * 		   &lt;xsd:extension&gt;
 * 	     &lt;xsd:complexContent&gt;
 * 	   &lt;xsd:complexType&gt;
 *   &lt;xsd:element&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class A extends InlineType implements HTMLinline, LinkReq, Target {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "a";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(InlineType.ATTRIBUTES)
                .put(Target.ATTRIBUTE, biConsumerString(getFieldOffset(A.class, "target")))
                .put(LinkReq.ATTRIBUTE, biConsumerUri(getFieldOffset(A.class, "href")))
                .build();
    }

    // Mandatory
    private Uri href;

    // Optional
    private String target;

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

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 1);
        writeLinkReq(writer, this);
        if (this.target != null) {
            writer.writeAttribute(Attributes.ADDRESS_TARGET, 6, getChars(this.target));
        }
        super.write(writer);
        writer.writeEnd(ADDRESS, 1);
    }

    @Override
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
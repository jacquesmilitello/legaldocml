package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.LinkReq;
import io.legaldocml.akn.group.AmendmentInline;
import io.legaldocml.akn.type.Uri;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.HREF;
import static io.legaldocml.akn.AknElements.RELATED_DOCUMENT;
import static io.legaldocml.akn.element.Attributes.attributeRequireGetterSetter4Uri;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element relatedDocument is an inline element to identify the document for which this document is a report of.
 * <p>
 * <pre>
 *   <xsd:element name="relatedDocument">
 * 	   <xsd:complexType mixed="true">
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="inline">
 * 		     <xsd:attributeGroup ref="link"/>
 * 		   </xsd:extension>
 * 		 </xsd:complexContent>
 * 	   </xsd:complexType>
 *   </xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class RelatedDocument extends InlineType implements LinkReq, AmendmentInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_RELATED_DOCUMENT = Buffers.address(RELATED_DOCUMENT);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(InlineType.ATTRIBUTES)
                .put(HREF, attributeRequireGetterSetter4Uri(HREF, getFieldOffset(RelatedDocument.class, "href")))
                .build();
    }

    private Uri href;

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
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_RELATED_DOCUMENT, 15);
        LinkReq.super.write(writer);
        super.write(writer);
        writer.writeEnd(ADDRESS_RELATED_DOCUMENT, 15);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return RELATED_DOCUMENT;
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
        if (visitor.visitEnter(this)) {
            super.accept(visitor);
            visitor.visitLeave(this);
        }
    }
}
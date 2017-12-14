package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.LinkReq;
import io.legaldocml.akn.group.ANinline;
import io.legaldocml.akn.type.Uri;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.HREF;
import static io.legaldocml.akn.AknElements.REF;
import static io.legaldocml.akn.util.XmlWriterHelper.writeLinkReq;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element ref is an inline element containing a legal reference.
 *
 * <pre>
 *   <xsd:element name="ref">
 * 	   <xsd:complexType mixed="true">
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="inlinereq">
 * 		     <xsd:attributeGroup ref="link"/>
 * 		   <xsd:extension>
 * 	     <xsd:complexContent>
 *     <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Ref extends InlineReqType implements ANinline, LinkReq {

    /**
     * Memory address.
     */
    private static final long ADDRESS_REF = Buffers.address(REF);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(InlineReqType.ATTRIBUTES)
                .put(HREF, Attributes.biConsumerUri(HREF, getFieldOffset(Ref.class, "href")))
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
        writer.writeStart(ADDRESS_REF, 3);
        writeLinkReq(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_REF, 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return REF;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }
}
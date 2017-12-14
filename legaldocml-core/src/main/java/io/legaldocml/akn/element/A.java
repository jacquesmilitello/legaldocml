package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.LinkReq;
import io.legaldocml.akn.attribute.Target;
import io.legaldocml.akn.group.HTMLinline;
import io.legaldocml.akn.type.Uri;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.HREF;
import static io.legaldocml.akn.AknAttributes.TARGET;
import static io.legaldocml.akn.AknElements.A;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.element.Attributes.biConsumerUri;
import static io.legaldocml.akn.util.XmlWriterHelper.writeLinkReq;
import static io.legaldocml.akn.util.XmlWriterHelper.writeTarget;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element a is an HTML element and is used in Akoma Ntoso as in HTML, for the generic link to a web resource (NOT
 * to an Akoma Ntoso document: use ref for that). It is an inline.
 * <pre>
 *   <xsd:element name="a">
 * 	   <xsd:complexType mixed="true">
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="inline">
 * 		     <xsd:attributeGroup ref="link"/>
 * 			 <xsd:attributeGroup ref="target"/>
 * 		   </xsd:extension>
 * 	     </xsd:complexContent>
 * 	   </xsd:complexType>
 *   </xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class A extends InlineType implements HTMLinline, LinkReq, Target {

    /**
     * Memory address.
     */
    private static final long ADDRESS_A = Buffers.address(A);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(InlineType.ATTRIBUTES)
                .put(TARGET, biConsumerString(TARGET, getFieldOffset(A.class, "target")))
                .put(HREF, biConsumerUri(HREF, getFieldOffset(A.class, "href")))
                .build();
    }

    // Mandatory
    private Uri href;

    // Optional
    private String target;

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
    public String getTarget() {
        return this.target;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_A, 1);
        writeLinkReq(writer, this);
        writeTarget(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_A, 1);
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
    public String name() {
        return A;
    }

}
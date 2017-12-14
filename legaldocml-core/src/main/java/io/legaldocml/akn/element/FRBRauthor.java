package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.LinkReq;
import io.legaldocml.akn.attribute.Role;
import io.legaldocml.akn.type.RoleRef;
import io.legaldocml.akn.type.Uri;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.util.ToStringBuilder;

import java.io.IOException;
import java.util.Objects;

import static io.legaldocml.akn.AknAttributes.AS;
import static io.legaldocml.akn.AknAttributes.HREF;
import static io.legaldocml.akn.AknElements.FRBR_AUTHOR;
import static io.legaldocml.akn.util.XmlWriterHelper.writeLinkReq;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRole;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element FRBRauthor is the metadata property containing a relevant author of the document in the respective level
 * of the FRBR hierarchy. Attribute as specifies the role of the author.
 * <p>
 * <pre>
 *   <xsd:element name="FRBRauthor">
 *     <xsd:complexType>
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="metaopt">
 * 		     <xsd:attributeGroup ref="link"/>
 * 			 <xsd:attributeGroup ref="role"/>
 * 		   <xsd:extension>
 *       <xsd:complexContent>
 *     <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRauthor extends MetaOpt implements LinkReq, Role {

    /**
     * Memory address.
     */
    private static final long ADDRESS_FRBR_AUTHOR = Buffers.address(FRBR_AUTHOR);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(MetaOpt.ATTRIBUTES)
                .put(HREF, Attributes.attributeGetterSetter4Uri(HREF, getFieldOffset(FRBRauthor.class, "href")))
                .put(AS, Attributes.attributeGetterSetter4RoleRef(AS, getFieldOffset(FRBRauthor.class, "as")))
                .build();
    }

    private Uri href;
    private RoleRef as;

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
    public RoleRef getAs() {
        return this.as;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAs(RoleRef as) {
        this.as = as;
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
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_FRBR_AUTHOR, 10);
        writeRole(writer, this);
        writeLinkReq(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_FRBR_AUTHOR, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return FRBR_AUTHOR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(java.lang.Object obj) {
        return obj == this || obj != null && obj instanceof FRBRauthor && Objects.equals(href, ((FRBRauthor) obj).href);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        if (href == null) {
            return super.hashCode();
        } else {
            return href.hashCode();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void toString(ToStringBuilder builder) {
        builder.append(HREF, this.href);
        builder.append(AS, this.as);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        visitor.visit(this);
    }

}
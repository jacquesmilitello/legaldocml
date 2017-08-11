package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.LinkReq;
import io.legaldocml.akn.attribute.Role;
import io.legaldocml.akn.type.RoleRef;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Uri;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.util.XmlWriterHelper.writeLinkReq;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRole;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element FRBRauthor is the metadata property containing a relevant author of the document in the respective level
 * of the FRBR hierarchy. Attribute as specifies the role of the author.
 * <p/>
 * <pre>
 *   <xsd:element name="FRBRauthor">
 *     <xsd:complexType>
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="metaopt">
 * 		     <xsd:attributeGroup ref="link"/>
 * 			 <xsd:attributeGroup ref="role"/>
 * 		   </xsd:extension>
 *       </xsd:complexContent>
 *     </xsd:complexType>
 *   </xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRauthor extends MetaOpt implements LinkReq, Role {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "FRBRauthor";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(MetaOpt.ATTRIBUTES)
                .put(LinkReq.ATTRIBUTE, Attributes.biConsumerUri(getFieldOffset(FRBRauthor.class, "href")))
                .put(Role.ATTRIBUTE, Attributes.biConsumerRoleRef(getFieldOffset(FRBRauthor.class, "as")))
                .build();
    }

    private Uri href;
    private RoleRef as;

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

    public RoleRef getAs() {
        return this.as;
    }

    public void setAs(RoleRef as) {
        this.as = as;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 10);
        writeRole(writer, this);
        writeLinkReq(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        // empty node => go to end.
        reader.nextStartOrEndElement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}
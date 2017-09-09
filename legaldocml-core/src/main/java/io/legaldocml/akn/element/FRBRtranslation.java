package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.attribute.Agent;
import io.legaldocml.akn.attribute.Authoritative;
import io.legaldocml.akn.attribute.FromLanguage;
import io.legaldocml.akn.attribute.LinkReq;
import io.legaldocml.akn.attribute.Pivot;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.util.Uri;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerAgentRef;
import static io.legaldocml.akn.element.Attributes.biConsumerBoolean;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.element.Attributes.biConsumerUri;
import static io.legaldocml.akn.util.XmlWriterHelper.writeAgent;
import static io.legaldocml.akn.util.XmlWriterHelper.writeAuthoritative;
import static io.legaldocml.akn.util.XmlWriterHelper.writeFromLanguage;
import static io.legaldocml.akn.util.XmlWriterHelper.writeLinkReq;
import static io.legaldocml.akn.util.XmlWriterHelper.writePivot;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element FRBRtranslation is the metadata property specifying the source of which this expression is a translation
 * of.
 *
 * <pre>
 *   <xsd:element name="FRBRtranslation">
 *     <xsd:complexType>
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="metaopt">
 * 		     <xsd:attributeGroup ref="link"/>
 *           <xsd:attributeGroup ref="fromLanguage"/>
 *           <xsd:attributeGroup ref="authoritative"/>
 *           <xsd:attributeGroup ref="pivot"/>
 *           <xsd:attributeGroup ref="agent"/>
 * 		   </xsd:extension>
 * 	     </xsd:complexContent>
 *     </xsd:complexType>
 *   </xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRtranslation extends MetaOpt implements LinkReq, Authoritative, Agent, Pivot, FromLanguage {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "FRBRtranslation";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(MetaOpt.ATTRIBUTES)
                .put(AknAttributes.HREF, biConsumerUri(getFieldOffset(FRBRtranslation.class, "href")))
                .put(AknAttributes.FROM_LANGUAGE, biConsumerString(getFieldOffset(FRBRtranslation.class, "fromLanguage")))
                .put(AknAttributes.BY, biConsumerAgentRef(getFieldOffset(FRBRtranslation.class, "by")))
                .put(AknAttributes.PIVOT, biConsumerString(getFieldOffset(FRBRtranslation.class, "pivot")))
                .put(AknAttributes.AUTHORITATIVE, biConsumerBoolean(getFieldOffset(FRBRtranslation.class, "authoritative")))
                .build();
    }


    // Mandatory (from Link)
    private Uri href;

    // Mandatory
    private String fromLanguage;

    // Mandatory
    private AgentRef by;

    // Optional
    private String pivot;

    // Optional
    private Boolean authoritative;

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

    /**
     * {@inheritDoc}
     */
    public String getFromLanguage() {
        return this.fromLanguage;
    }

    /**
     * {@inheritDoc}
     */
    public void setFromLanguage(String fromLanguage) {
        this.fromLanguage = fromLanguage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPivot() {
        return this.pivot;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPivot(String pivot) {
        this.pivot = pivot;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AgentRef getBy() {
        return this.by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBy(AgentRef by) {
        this.by = by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean getAuthoritative() {
        return this.authoritative;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAuthoritative(Boolean authoritative) {
        this.authoritative = authoritative;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 15);
        writeLinkReq(writer, this);
        writeFromLanguage(writer, this);
        writeAgent(writer, this);
        writeAuthoritative(writer, this);
        writePivot(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS, 15);
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
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
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
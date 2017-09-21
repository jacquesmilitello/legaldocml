package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.attribute.LinkOpt;
import io.legaldocml.akn.attribute.Quote;
import io.legaldocml.akn.group.ANinline;
import io.legaldocml.util.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.util.Uri;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.AknElements.EMBEDDED_TEXT;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.element.Attributes.biConsumerUri;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * the element embeddedText is an inline element containing a string used as an extract from another document. Attribute
 * quote is used to specify the quote character used in the original; no quote attribute implies that the quote is left
 * in the text; quote="" implies that there is no quote character.
 *
 * <pre>
 *   <xsd:element name="embeddedText">
 *     <xsd:complexType mixed="true">
 *       <xsd:complexContent>
 *         <xsd:extension base="inline">
 *           <xsd:attributeGroup ref="quote"/>
 *           <xsd:attributeGroup ref="linkopt"/>
 *         <xsd:extension>
 *       <xsd:complexContent>
 *     <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class EmbeddedText extends InlineType implements ANinline, Quote, LinkOpt {

    /**
     * Memory address.
     */
    private static final long ADDRESS_EMBEDDED_TEXT = Buffers.address(EMBEDDED_TEXT);

    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(SubFlowStructure.ATTRIBUTES)
                .put(AknAttributes.HREF, biConsumerUri(getFieldOffset(EmbeddedText.class, "href")))
                .put(AknAttributes.START_QUOTE, biConsumerString(getFieldOffset(EmbeddedText.class, "startQuote")))
                .put(AknAttributes.END_QUOTE, biConsumerString(getFieldOffset(EmbeddedText.class, "endQuote")))
                .put(AknAttributes.INLINE_QUOTE, biConsumerString(getFieldOffset(EmbeddedText.class, "inlineQuote")))
                .build();
    }

    private Uri href;
    private String startQuote;
    private String endQuote;
    private String inlineQuote;

    /**
     * {@inheritDoc}
     */
    @Override
    public Uri getHref() {
        return href;
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
    public String getStartQuote() {
        return startQuote;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStartQuote(String startQuote) {
        this.startQuote = startQuote;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEndQuote() {
        return endQuote;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEndQuote(String endQuote) {
        this.endQuote = endQuote;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInlineQuote() {
        return inlineQuote;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInlineQuote(String inlineQuote) {
        this.inlineQuote = inlineQuote;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_EMBEDDED_TEXT, 11);
        super.write(writer);
        writer.writeEnd(ADDRESS_EMBEDDED_TEXT, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return EMBEDDED_TEXT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }
}
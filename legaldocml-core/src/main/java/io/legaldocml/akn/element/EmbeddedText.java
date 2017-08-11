package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.LinkOpt;
import io.legaldocml.akn.attribute.Quote;
import io.legaldocml.akn.group.ANinline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Uri;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.element.Attributes.biConsumerUri;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * the element embeddedText is an inline element containing a string used as an extract from another document. Attribute
 * quote is used to specify the quote character used in the original; no quote attribute implies that the quote is left
 * in the text; quote="" implies that there is no quote character.
 * <p/>
 * <pre>
 *   <xsd:element name="embeddedText">
 *     <xsd:complexType mixed="true">
 *       <xsd:complexContent>
 *         <xsd:extension base="inline">
 *           <xsd:attributeGroup ref="quote"/>
 *           <xsd:attributeGroup ref="linkopt"/>
 *         </xsd:extension>
 *       </xsd:complexContent>
 *     </xsd:complexType>
 *   </xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class EmbeddedText extends InlineType implements ANinline, Quote, LinkOpt {
    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "embeddedText";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(SubFlowStructure.ATTRIBUTES)
                .put(LinkOpt.ATTRIBUTE, biConsumerUri(getFieldOffset(EmbeddedText.class, "href")))
                .put(Quote.ATTRIBUTE_STARTQUOTE, biConsumerString(getFieldOffset(EmbeddedText.class, "startQuote")))
                .put(Quote.ATTRIBUTE_ENDQUOTE, biConsumerString(getFieldOffset(EmbeddedText.class, "endQuote")))
                .put(Quote.ATTRIBUTE_INLINEQUOTE, biConsumerString(getFieldOffset(EmbeddedText.class, "inlineQuote")))
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
        writer.writeStart(ADDRESS, 11);
        super.write(writer);
        writer.writeEnd(ADDRESS, 11);
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
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }
}
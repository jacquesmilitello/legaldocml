package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.attribute.For;
import io.legaldocml.akn.attribute.Quote;
import io.legaldocml.akn.type.EidRef;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.AknElements.QUOTED_TEXT;
import static io.legaldocml.akn.element.Attributes.biConsumerEidRef;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.util.XmlWriterHelper.writeFor;
import static io.legaldocml.akn.util.XmlWriterHelper.writeQuote;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * the element quotedText is an inline element containing a small string that is used either as the text being replaced,
 * or the replacement, or the positioning at which some modification should take place. Attribute quote is used to
 * specify the quote character used in the original; no quote attribute implies that the quote is left in the text;
 * quote="" implies that there is no quote character. Attribute for is used to point to the eId of the corresponding ref
 * element.
 *
 * <pre>
 *   <xsd:element name="quotedText">
 * 	   <xsd:complexType mixed="true">
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="inline">
 * 		     <xsd:attributeGroup ref="quote"/>
 *           <xsd:attributeGroup ref="for"/>
 * 	 	   <xsd:extension>
 * 	     <xsd:complexContent>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class QuotedText extends InlineType implements For, Quote, ModTypeItem {

    /**
     * Memory address.
     */
    private static final long ADDRESS_QUOTED_TEXT = Buffers.address(QUOTED_TEXT);

    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(InlineType.ATTRIBUTES)
                .put(AknAttributes.FOR, biConsumerEidRef(getFieldOffset(QuotedText.class, "_for")))
                .put(AknAttributes.START_QUOTE, biConsumerString(getFieldOffset(QuotedText.class, "startQuote")))
                .put(AknAttributes.END_QUOTE, biConsumerString(getFieldOffset(QuotedText.class, "endQuote")))
                .put(AknAttributes.INLINE_QUOTE, biConsumerString(getFieldOffset(QuotedText.class, "inlineQuote")))
                .build();

    }

    private EidRef _for;
    private String startQuote;
    private String endQuote;
    private String inlineQuote;

    /**
     * {@inheritDoc}
     */
    @Override
    public EidRef getFor() {
        return this._for;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFor(EidRef _for) {
        this._for = _for;
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
        writer.writeStart(ADDRESS_QUOTED_TEXT, 10);
        writeFor(writer, this);
        writeQuote(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_QUOTED_TEXT, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return QUOTED_TEXT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }

}

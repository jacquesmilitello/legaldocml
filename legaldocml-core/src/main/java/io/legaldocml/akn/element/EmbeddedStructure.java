package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.LinkOpt;
import io.legaldocml.akn.attribute.Quote;
import io.legaldocml.akn.group.ANinline;
import io.legaldocml.akn.type.Uri;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.END_QUOTE;
import static io.legaldocml.akn.AknAttributes.HREF;
import static io.legaldocml.akn.AknAttributes.INLINE_QUOTE;
import static io.legaldocml.akn.AknAttributes.START_QUOTE;
import static io.legaldocml.akn.AknElements.EMBEDDED_STRUCTURE;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4Uri;
import static io.legaldocml.akn.util.XmlWriterHelper.writeLinkOpt;
import static io.legaldocml.akn.util.XmlWriterHelper.writeQuote;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * the element embeddedStructure is a subFlow element containing a full structure used as an extract from another
 * document or position. CoreAttribute quote is used to specify the quote character used in the original; no quote attribute
 * implies that the quote is left in the text; quote="" implies that there is no quote character.
 * <p>
 * <pre>
 *   <xsd:element name="embeddedStructure">
 * 	   <xsd:complexType>
 *       <xsd:complexContent>
 *         <xsd:extension base="subFlowStructure">
 *           <xsd:attributeGroup ref="quote"/>
 *           <xsd:attributeGroup ref="linkopt"/>
 *         </xsd:extension>
 *       </xsd:complexContent>
 *     </xsd:complexType>
 * 	 </xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class EmbeddedStructure extends SubFlowStructure implements LinkOpt, Quote, ANinline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_EMBEDDED_STRUCTURE = Buffers.address(EMBEDDED_STRUCTURE);


    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(SubFlowStructure.ATTRIBUTES)
                .put(HREF, attributeGetterSetter4Uri(HREF, getFieldOffset(EmbeddedStructure.class, "href")))
                .put(START_QUOTE, attributeGetterSetter4String(START_QUOTE, getFieldOffset(EmbeddedStructure.class, "startQuote")))
                .put(END_QUOTE, attributeGetterSetter4String(END_QUOTE, getFieldOffset(EmbeddedStructure.class, "endQuote")))
                .put(INLINE_QUOTE, attributeGetterSetter4String(INLINE_QUOTE, getFieldOffset(EmbeddedStructure.class, "inlineQuote")))
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
        writer.writeStart(ADDRESS_EMBEDDED_STRUCTURE, 17);
        writeLinkOpt(writer, this);
        writeQuote(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_EMBEDDED_STRUCTURE, 17);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return EMBEDDED_STRUCTURE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }
}
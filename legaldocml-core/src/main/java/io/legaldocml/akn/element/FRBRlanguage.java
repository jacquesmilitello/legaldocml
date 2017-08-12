package io.legaldocml.akn.element;


import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Language;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;
import static io.legaldocml.unsafe.UnsafeString.getChars;

/**
 * The element FRBRlanguage is the metadata property containing a RFC4646 (three-letter code) of the main human language
 * used in the content of this expression.
 *
 * <pre>
 *   &lt;xsd:element name="FRBRlanguage"&gt;
 *     &lt;xsd:complexType&gt;
 * 	     &lt;xsd:complexContent&gt;
 * 		   &lt;xsd:extension base="metaopt"&gt;
 * 		     &lt;xsd:attributeGroup ref="language"/&gt;
 * 		   &lt;xsd:extension&gt;
 * 	     &lt;xsd:complexContent&gt;
 *     &lt;xsd:complexType&gt;
 *   &lt;xsd:element&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRlanguage extends MetaOpt implements Language {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "FRBRlanguage";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(MetaOpt.ATTRIBUTES)
                .put(Language.ATTRIBUTE, biConsumerString(getFieldOffset(FRBRlanguage.class, "language")))
                .build();
    }

    private String language;

    /**
     * {@inheritDoc}
     */
    public String getLanguage() {
        return language;
    }

    /**
     * {@inheritDoc}
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 12);
        if (this.language != null) {
            writer.writeAttribute(Attributes.ADDRESS_LANGUAGE, 8, getChars(this.language));
        } else {

        }
        super.write(writer);
        writer.writeEnd(ADDRESS, 12);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }

}
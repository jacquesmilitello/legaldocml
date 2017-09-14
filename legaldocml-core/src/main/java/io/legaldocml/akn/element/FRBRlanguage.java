package io.legaldocml.akn.element;


import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.MandatoryAttributeException;
import io.legaldocml.akn.attribute.Language;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.AknElements.FRBR_LANGUAGE;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.util.XmlWriterHelper.throwException;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;
import static io.legaldocml.unsafe.UnsafeString.getChars;

/**
 * The element FRBRlanguage is the metadata property containing a RFC4646 (three-letter code) of the main human language
 * used in the content of this expression.
 *
 * <pre>
 *   <xsd:element name="FRBRlanguage">
 *     <xsd:complexType>
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="metaopt">
 * 		     <xsd:attributeGroup ref="language"/>
 * 		   <xsd:extension>
 * 	     <xsd:complexContent>
 *     <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRlanguage extends MetaOpt implements Language {

    /**
     * Memory address.
     */
    private static final long ADDRESS_FRBR_LANGUAGE = Buffers.address(FRBR_LANGUAGE);

    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(MetaOpt.ATTRIBUTES)
                .put(AknAttributes.LANGUAGE, biConsumerString(getFieldOffset(FRBRlanguage.class, "language")))
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
        writer.writeStart(ADDRESS_FRBR_LANGUAGE, 12);
        if (this.language != null) {
            writer.writeAttribute(Attributes.ADDRESS_LANGUAGE, 8, getChars(this.language));
        } else {
            throwException(writer, new MandatoryAttributeException(this, AknAttributes.LANGUAGE, writer));
        }
        super.write(writer);
        writer.writeEnd(ADDRESS_FRBR_LANGUAGE, 12);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return FRBR_LANGUAGE;
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
package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.TextualModType;
import io.legaldocml.akn.type.TextualMods;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.TYPE;
import static io.legaldocml.akn.AknElements.NEW;
import static io.legaldocml.akn.AknElements.OLD;
import static io.legaldocml.akn.AknElements.PREVIOUS;
import static io.legaldocml.akn.AknElements.TEXTUAL_MOD;
import static io.legaldocml.akn.element.Attributes.biConsumerEnum;
import static io.legaldocml.akn.util.XmlWriterHelper.writeTextualModType;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element textualMod is a metadata element specifying an (active or passive) textual modification for the document.
 * <p>
 * <pre>
 *   <xsd:element name="textualMod">
 * 	   <xsd:complexType>
 * 	     <xsd:complexContent>
 * 	 	   <xsd:extension base="modificationType">
 * 		     <xsd:sequence>
 * 			   <xsd:element ref="previous" minOccurs="0" maxOccurs="1"/>
 * 			   <xsd:element ref="old" minOccurs="0" maxOccurs="1" />
 * 			   <xsd:element ref="new" minOccurs="0" maxOccurs="1" />
 * 			 <xsd:sequence>
 * 			 <xsd:attributeGroup ref="textualModType"/>
 * 		   <xsd:extension>
 * 	     <xsd:complexContent>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class TextualMod extends ModificationType implements TextualModType, AmendmentsElement {

    protected static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(ModificationType.ATTRIBUTES)
                .put(TYPE, biConsumerEnum(TYPE, getFieldOffset(TextualMod.class, "type"), TextualMods.class))
                .build();
    }


    /**
     * Memory address.
     */
    private static final long ADDRESS_TEXTUAL_MOD = Buffers.address(TEXTUAL_MOD);

    private TextualMods type;

    private Previous previous;
    private Old old;
    private New _new;

    /**
     * {@inheritDoc}
     */
    @Override
    public TextualMods getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setType(TextualMods type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_TEXTUAL_MOD, 10);
        writeTextualModType(writer, this);
        super.write(writer);
        if (this.previous != null) {
            this.previous.write(writer);
        }
        if (this.old != null) {
            this.old.write(writer);
        }
        if (this._new != null) {
            this._new.write(writer);
        }
        writer.writeEnd(ADDRESS_TEXTUAL_MOD, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);

        if (reader.getQName().equalsLocalName(PREVIOUS)) {
            this.previous = new Previous();
            this.previous.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(OLD)) {
            this.old = new Old();
            this.old.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(NEW)) {
            this._new = new New();
            this._new.read(reader);
            reader.nextStartOrEndElement();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return TEXTUAL_MOD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }

}
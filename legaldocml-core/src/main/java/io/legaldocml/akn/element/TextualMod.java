package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.TextualModType;
import io.legaldocml.akn.type.TextualMods;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerEnum;
import static io.legaldocml.akn.util.XmlWriterHelper.writeTextualModType;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element textualMod is a metadata element specifying an (active or passive) textual modification for the document.
 * <p/>
 * <pre>
 *   <xsd:element name="textualMod">
 * 	   <xsd:complexType>
 * 	     <xsd:complexContent>
 * 	 	   <xsd:extension base="modificationType">
 * 		     <xsd:sequence>
 * 			   <xsd:element ref="previous" minOccurs="0" maxOccurs="1"/>
 * 			   <xsd:element ref="old" minOccurs="0" maxOccurs="1" />
 * 			   <xsd:element ref="new" minOccurs="0" maxOccurs="1" />
 * 			 </xsd:sequence>
 * 			 <xsd:attributeGroup ref="textualModType"/>
 * 		   </xsd:extension>
 * 	     </xsd:complexContent>
 * 	   </xsd:complexType>
 *   </xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class TextualMod extends ModificationType implements TextualModType, AmendmentsElement {

    protected static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(ModificationType.ATTRIBUTES)
                .put(TextualModType.ATTRIBUTE, biConsumerEnum(getFieldOffset(TextualMod.class, "type"), TextualMods.class))
                .build();
    }

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "textualMod";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

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
        writer.writeStart(ADDRESS, 10);
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
        writer.writeEnd(ADDRESS, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);

        if (reader.getQName().equalsLocalName(Previous.ELEMENT)) {
            this.previous = new Previous();
            this.previous.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(Old.ELEMENT)) {
            this.old = new Old();
            this.old.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(New.ELEMENT)) {
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
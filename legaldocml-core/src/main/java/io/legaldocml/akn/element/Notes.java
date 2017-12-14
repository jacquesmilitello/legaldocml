package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Source;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.SOURCE;
import static io.legaldocml.akn.AknElements.NOTE;
import static io.legaldocml.akn.AknElements.NOTES;
import static io.legaldocml.akn.element.Attributes.biConsumerAgentRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writeSource;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * <pre>
 *   <xsd:element name="notes">
 * 	   <xsd:complexType>
 *       <xsd:sequence minOccurs="1" maxOccurs="unbounded">
 *  	   <xsd:element ref="note"/>
 *   	 <xsd:sequence>
 * 	 	 <xsd:attributeGroup ref="source"/>
 * 		 <xsd:attributeGroup ref="xmllang"/>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Notes implements Source {



    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(NOTES);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .put(SOURCE, biConsumerAgentRef(SOURCE, getFieldOffset(Notes.class, "source")))
                .build();
    }

    // Mandatory (min 1)
    private final AknList<Note> elements = new AknList<Note>(new Note[4]);

    // Mandatory
    private AgentRef source;

    /**
     * {@inheritDoc}
     */
    @Override
    public AgentRef getSource() {
        return this.source;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSource(AgentRef source) {
        this.source = source;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 5);
        writeSource(writer, this);
        this.elements.write(writer);
        writer.writeEnd(ADDRESS, 5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        Attributes.read(reader, this);
        reader.nextStartOrEndElement();

        if (reader.getQName().equalsLocalName(NOTE)) {
            Note note;
            do {
                note = new Note();
                note.read(reader);
                this.elements.add(note);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(NOTE));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return NOTES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            this.elements.accept(visitor);
            visitor.visitLeave(this);
        }
    }

}
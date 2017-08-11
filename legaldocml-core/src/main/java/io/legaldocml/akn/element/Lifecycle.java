package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Source;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlWriterHelper;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerAgentRef;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * <pre>
 *   <xsd:element name="lifecycle">
 * 	   <xsd:complexType>
 * 	     <xsd:sequence>
 * 		   <xsd:element ref="eventRef" minOccurs="1" maxOccurs="unbounded"/>
 * 		 </xsd:sequence>
 * 		 <xsd:attributeGroup ref="source"/>
 * 	   </xsd:complexType>
 *   </xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Lifecycle implements Source {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "lifecycle";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .put(Source.ATTRIBUTE, biConsumerAgentRef(getFieldOffset(Lifecycle.class, "source")))
                .build();
    }

    // Mandatory (min 1)
    private final AknList<EventRef> eventRefs = new AknList<EventRef>(new EventRef[6]);

    // Optional
    private AgentRef source;

    /**
     * {@inheritDoc}
     */
    public AgentRef getSource() {
        return this.source;
    }

    /**
     * {@inheritDoc}
     */
    public void setSource(AgentRef source) {
        this.source = source;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 9);
        XmlWriterHelper.writeSource(writer, this);
        this.eventRefs.write(writer);
        writer.writeEnd(ADDRESS, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        Attributes.read(reader, this);
        reader.nextStartOrEndElement();

        if (reader.getQName().equalsLocalName(EventRef.ELEMENT)) {
            EventRef eventRef;
            do {
                eventRef = new EventRef();
                eventRef.read(reader);
                this.eventRefs.add(eventRef);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(EventRef.ELEMENT));
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

    @Override
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }
}
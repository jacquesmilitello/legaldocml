package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Source;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlWriterHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.SOURCE;
import static io.legaldocml.akn.AknElements.EVENT_REF;
import static io.legaldocml.akn.AknElements.LIFECYCLE;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4AgentRef;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * <pre>
 *   <xsd:element name="lifecycle">
 * 	   <xsd:complexType>
 * 	     <xsd:sequence>
 * 		   <xsd:element ref="eventRef" minOccurs="1" maxOccurs="unbounded"/>
 * 		 <xsd:sequence>
 * 		 <xsd:attributeGroup ref="source"/>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Lifecycle implements Source {

    /**
     * Memory address.
     */
    private static final long ADDRESS_LIFECYCLE = Buffers.address(LIFECYCLE);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .put(SOURCE, attributeGetterSetter4AgentRef(SOURCE, getFieldOffset(Lifecycle.class, "source")))
                .build();
    }

    // Mandatory (min 1)
    private final AknList<EventRef> eventRefs = new AknList<>(new EventRef[6]);

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
        writer.writeStart(ADDRESS_LIFECYCLE, 9);
        XmlWriterHelper.writeSource(writer, this);
        this.eventRefs.write(writer);
        writer.writeEnd(ADDRESS_LIFECYCLE, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        Attributes.read(reader, this);
        reader.nextStartOrEndElement();

        if (reader.getQName().equalsLocalName(EVENT_REF)) {
            EventRef eventRef;
            do {
                eventRef = new EventRef();
                eventRef.read(reader);
                this.eventRefs.add(eventRef);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(EVENT_REF));
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return LIFECYCLE;
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
            this.eventRefs.accept(visitor);
            visitor.visitLeave(this);
        }
    }
}
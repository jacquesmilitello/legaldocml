package io.legaldocml.akn.element;

import io.legaldocml.akn.attribute.Core;
import io.legaldocml.akn.attribute.IdReq;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.TEMPORAL_GROUP;
import static io.legaldocml.akn.AknElements.TIME_INTERVAL;

/**
 *
 * <pre>
 *   <xsd:element name="temporalGroup">
 *     <xsd:complexType>
 *       <xsd:sequence>
 *         <xsd:element ref="timeInterval" minOccurs="1" maxOccurs="unbounded"/>
 *       <xsd:sequence>
 *       <xsd:attributeGroup ref="core"/>
 *       <xsd:attributeGroup ref="idreq"/>
 *     <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class TemporalGroup extends AbstractIdCore implements Core, IdReq {

    /**
     * Memory address.
     */
    private static final long ADDRESS_TEMPORAL_GROUP = Buffers.address(TEMPORAL_GROUP);

    // Mandatory (min 1).
    private final AknList<TimeInterval> elements = new AknList<>(new TimeInterval[4]);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_TEMPORAL_GROUP, 13);
        IdReq.super.write(writer);
        super.write(writer);
        this.elements.write(writer);
        writer.writeEnd(ADDRESS_TEMPORAL_GROUP, 13);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        reader.nextStartOrEndElement();
        if (reader.getQName().equalsLocalName(TIME_INTERVAL)) {
            TimeInterval timeInterval;
            do {
                timeInterval = new TimeInterval();
                timeInterval.read(reader);
                this.elements.add(timeInterval);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(TIME_INTERVAL));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return TEMPORAL_GROUP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            elements.accept(visitor);
            visitor.visitLeave(this);
        }
    }

}
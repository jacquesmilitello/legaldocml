package io.legaldocml.akn.element;

import io.legaldocml.akn.attribute.Core;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

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
public final class TemporalGroup extends IdReqImpl implements Core {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "temporalGroup";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    // Mandatory (min 1).
    private final AknList<TimeInterval> elements = new AknList<TimeInterval>(new TimeInterval[4]);


    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 13);
        super.write(writer);
        this.elements.write(writer);
        writer.writeEnd(ADDRESS, 13);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        reader.nextStartOrEndElement();
        if (reader.getQName().equalsLocalName(TimeInterval.ELEMENT)) {
            TimeInterval timeInterval;
            do {
                timeInterval = new TimeInterval();
                timeInterval.read(reader);
                this.elements.add(timeInterval);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(TimeInterval.ELEMENT));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}
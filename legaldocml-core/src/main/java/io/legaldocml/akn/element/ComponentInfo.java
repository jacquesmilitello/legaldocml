package io.legaldocml.akn.element;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * <pre>
 *   <xsd:element name="componentInfo">
 * 	   <xsd:complexType>
 * 	     <xsd:sequence minOccurs="0" maxOccurs="unbounded">
 * 	 	   <xsd:element ref="componentData"/>
 * 	     </xsd:sequence>
 * 	   </xsd:complexType>
 *   </xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ComponentInfo implements AknObject {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "componentInfo";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private AknList<ComponentData> componentData;

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 13);
        if (this.componentData != null) {
            this.componentData.write(writer);
        }
        writer.writeEnd(ADDRESS, 13);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        reader.nextStartOrEndElement();
        if (reader.getQName().equalsLocalName(ComponentData.ELEMENT)) {
            ComponentData data;
            this.componentData = new AknList<>(new ComponentData[4]);
            do {
                data = new ComponentData();
                data.read(reader);
                this.componentData.add(data);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(ComponentData.ELEMENT));
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
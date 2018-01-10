package io.legaldocml.akn.element;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.COMPONENT_DATA;
import static io.legaldocml.akn.AknElements.COMPONENT_INFO;

/**
 * <pre>
 *   <xsd:element name="componentInfo">
 * 	   <xsd:complexType>
 * 	     <xsd:sequence minOccurs="0" maxOccurs="unbounded">
 * 	 	   <xsd:element ref="componentData"/>
 * 	     <xsd:sequence>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ComponentInfo implements AknObject {

    /**
     * Memory address.
     */
    private static final long ADDRESS_COMPONENT_INFO = Buffers.address(COMPONENT_INFO);

    private AknList<ComponentData> componentData;

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_COMPONENT_INFO, 13);
        if (this.componentData != null) {
            this.componentData.write(writer);
        }
        writer.writeEnd(ADDRESS_COMPONENT_INFO, 13);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        reader.nextStartOrEndElement();
        if (reader.getQName().equalsLocalName(COMPONENT_DATA)) {
            ComponentData data;
            this.componentData = new AknList<>(new ComponentData[4]);
            do {
                data = new ComponentData();
                data.read(reader);
                this.componentData.add(data);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(COMPONENT_DATA));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return COMPONENT_INFO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            if (this.componentData != null) {
                this.componentData.accept(visitor);
            }
            visitor.visitLeave(this);
        }
    }
}
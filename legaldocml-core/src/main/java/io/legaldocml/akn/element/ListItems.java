package io.legaldocml.akn.element;

import io.legaldocml.akn.util.AknList;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * the complex type listItems specifies the content model of elements ul and ol, and specifies just a sequence of list
 * items (elements li).
 *
 * <pre>
 *   <xsd:complexType name="listItems">
 * 	   <xsd:sequence>
 *       <xsd:element ref="li" minOccurs="1" maxOccurs="unbounded"/>
 * 	   <xsd:sequence>
 * 	   <xsd:attributeGroup ref="corereq"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class ListItems extends CoreReqImpl {

    private final AknList<Li> lis = new AknList<>(new Li[4]);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        super.write(writer);
        this.lis.write(writer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        reader.nextStartOrEndElement();

        if (reader.getQName().equalsLocalName(Li.ELEMENT)) {
            Li li;
            do {
                li = new Li();
                li.read(reader);
                this.lis.add(li);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(Li.ELEMENT));
        }

    }

}
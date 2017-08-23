package io.legaldocml.akn.element;

import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The blockListType is the type of element blockList.
 *
 * <pre>
 *   <xsd:complexType name="blockListType">
 * 	   <xsd:sequence>
 *       <xsd:element ref="listIntroduction" minOccurs="0" maxOccurs="1"/>
 * 		 <xsd:element ref="item" minOccurs="1" maxOccurs="unbounded"/>
 * 		 <xsd:element ref="listWrapUp" minOccurs="0" maxOccurs="1"/>
 * 	   <xsd:sequence>
 * 	 <xsd:attributeGroup ref="corereq"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class BlockListType extends CoreReqImpl {

    private ListIntroduction listIntroduction;
    private final AknList<Item> items = new AknList<Item>(new Item[4]);
    private ListWrapUp listWrapUp;

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        super.write(writer);
        if (this.listIntroduction != null) {
            this.listIntroduction.write(writer);
        }
        items.write(writer);
        if (this.listWrapUp != null) {
            this.listWrapUp.write(writer);
        }
    }

    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        reader.nextStartOrEndElement();
        if (reader.getQName().equalsLocalName(ListIntroduction.ELEMENT)) {
            this.listIntroduction = new ListIntroduction();
            this.listIntroduction.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(Item.ELEMENT)) {
            Item item;
            do {
                item = new Item();
                item.read(reader);
                this.items.add(item);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(Item.ELEMENT));
        }

        if (reader.getQName().equalsLocalName(ListWrapUp.ELEMENT)) {
            this.listWrapUp = new ListWrapUp();
            this.listWrapUp.read(reader);
            reader.nextStartOrEndElement();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
       if (this.listIntroduction != null) {
           this.listIntroduction.accept(visitor);
       }
       this.items.accept(visitor);
       if (this.listWrapUp != null) {
           this.listWrapUp.accept(visitor);
       }
    }

}
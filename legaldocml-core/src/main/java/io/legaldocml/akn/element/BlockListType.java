package io.legaldocml.akn.element;

import io.legaldocml.akn.attribute.CoreReq;
import io.legaldocml.akn.container.Container;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.ListIterable;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.ITEM;
import static io.legaldocml.akn.AknElements.LIST_INTRODUCTION;
import static io.legaldocml.akn.AknElements.LIST_WRAP_UP;
import static java.util.Objects.requireNonNull;

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
public abstract class BlockListType extends AbstractCore implements CoreReq, Container<Item> {

    private ListIntroduction listIntroduction;
    private final AknList<Item> items = new AknList<>(new Item[4]);
    private ListWrapUp listWrapUp;

    public final ListIntroduction getListIntroduction() {
        return this.listIntroduction;
    }

    public final void setListIntroduction(ListIntroduction listIntroduction) {
        this.listIntroduction = listIntroduction;
    }

    public final ListWrapUp getListWrapUp() {
        return this.listWrapUp;
    }

    public final void setListWrapUp(ListWrapUp listWrapUp) {
        this.listWrapUp = listWrapUp;
    }

    public final void add(Item item) {
        this.items.add(requireNonNull(item));
    }

    public final void add(int index, Item item) {
        this.items.add(index, requireNonNull(item));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final ListIterable<Item> iterable() {
        return this.items.iterable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        CoreReq.super.write(writer);
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
        if (reader.getQName().equalsLocalName(LIST_INTRODUCTION)) {
            this.listIntroduction = new ListIntroduction();
            this.listIntroduction.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(ITEM)) {
            Item item;
            do {
                item = new Item();
                item.read(reader);
                this.items.add(item);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(ITEM));
        }

        if (reader.getQName().equalsLocalName(LIST_WRAP_UP)) {
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
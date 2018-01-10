package io.legaldocml.akn.element;

import io.legaldocml.akn.DocContainerTypeElement;
import io.legaldocml.akn.attribute.CoreReq;
import io.legaldocml.akn.group.ANblock;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.TOC;
import static io.legaldocml.akn.AknElements.TOC_ITEM;

/**
 * <pre>
 *   <xsd:element name="toc">
 * 	   <xsd:complexType>
 * 	     <xsd:sequence>
 * 		   <xsd:element ref="tocItem" minOccurs="1" maxOccurs="unbounded"/>
 * 		 <xsd:sequence>
 * 	     <xsd:attributeGroup ref="corereq"/>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Toc extends AbstractCore implements CoreReq, ANblock, DocContainerTypeElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_TOC = Buffers.address(TOC);

    private final AknList<TocItem> tocItems = new AknList<>(new TocItem[6]);

    public void add(TocItem item) {
        this.tocItems.add(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_TOC, 3);
        CoreReq.super.write(writer);
        this.tocItems.write(writer);
        writer.writeEnd(ADDRESS_TOC, 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        reader.nextStartOrEndElement();

        if (reader.getQName().equalsLocalName(TOC_ITEM)) {
            TocItem item;
            do {
                item = new TocItem();
                item.read(reader);
                this.tocItems.add(item);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(TOC_ITEM));
        } else {
            throw new RuntimeException("MISSING .....");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return TOC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            this.tocItems.accept(visitor);
            visitor.visitLeave(this);
        }
    }

}
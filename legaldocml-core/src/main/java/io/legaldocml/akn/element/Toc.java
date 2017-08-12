package io.legaldocml.akn.element;

import io.legaldocml.akn.DocContainerTypeElement;
import io.legaldocml.akn.group.ANblock;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * <pre>
 *   &lt;xsd:element name="toc"&gt;
 * 	   &lt;xsd:complexType&gt;
 * 	     &lt;xsd:sequence&gt;
 * 		   &lt;xsd:element ref="tocItem" minOccurs="1" maxOccurs="unbounded"/&gt;
 * 		 &lt;xsd:sequence&gt;
 * 	     &lt;xsd:attributeGroup ref="corereq"/&gt;
 * 	   &lt;xsd:complexType&gt;
 *   &lt;xsd:element&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Toc extends CoreReqImpl implements ANblock, DocContainerTypeElement {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "toc";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private final AknList<TocItem> tocItems = new AknList<TocItem>(new TocItem[6]);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 3);
        this.tocItems.write(writer);
        writer.writeEnd(ADDRESS, 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        reader.nextStartOrEndElement();

        if (reader.getQName().equalsLocalName(TocItem.ELEMENT)) {
            TocItem item;
            do {
                item = new TocItem();
                item.read(reader);
                this.tocItems.add(item);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(TocItem.ELEMENT));
        } else {
            throw new RuntimeException("MISSING .....");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
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
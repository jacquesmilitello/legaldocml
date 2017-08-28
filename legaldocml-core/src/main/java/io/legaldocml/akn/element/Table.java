package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.TableAtts;
import io.legaldocml.akn.group.HTMLblock;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerInteger;
import static io.legaldocml.akn.util.XmlWriterHelper.writeTableAtts;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * <pre>
 *   <xsd:element name="table">
 * 	   <xsd:complexType>
 * 	     <xsd:sequence>
 * 		   <xsd:element ref="caption" minOccurs="0" maxOccurs="1"/>
 * 		   <xsd:element ref="tr" minOccurs="1" maxOccurs="unbounded"/>
 * 		 <xsd:sequence>
 * 		 <xsd:attributeGroup ref="corereq"/>
 * 		 <xsd:attributeGroup ref="tableAtts"/>
 *     <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Table extends CoreReqImpl implements TableAtts, HTMLblock {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "table";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(Blocksopt.ATTRIBUTES)
                .put(AknAttributes.WIDTH, biConsumerInteger(getFieldOffset(Table.class, "width")))
                .put(AknAttributes.BORDER, biConsumerInteger(getFieldOffset(Table.class, "border")))
                .put(AknAttributes.CELLSPACING, biConsumerInteger(getFieldOffset(Table.class, "cellspacing")))
                .put(AknAttributes.CELLPADDING, biConsumerInteger(getFieldOffset(Table.class, "cellpadding")))
                .build();
    }

    private final AknList<Tr> trs = new AknList<Tr>(new Tr[4]);
    private Caption caption;
    private Integer width;
    private Integer border;
    private Integer cellspacing;
    private Integer cellpadding;

    public Caption getCaption() {
        return this.caption;
    }

    public void setCaption(Caption caption) {
        this.caption = caption;
    }

    public void addTr(Tr tr) {
        this.trs.add(tr);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getWidth() {
        return this.width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getBorder() {
        return this.border;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBorder(Integer border) {
        this.border = border;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getCellspacing() {
        return this.cellspacing;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCellspacing(Integer cellspacing) {
        this.cellspacing = cellspacing;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getCellpadding() {
        return this.cellpadding;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCellpadding(Integer cellpadding) {
        this.cellpadding = cellpadding;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 5);
        writeTableAtts(writer, this);
        super.write(writer);
        if (this.caption != null) {
            this.caption.write(writer);
        }
        this.trs.write(writer);
        writer.writeEnd(ADDRESS, 5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        reader.nextStartOrEndElement();

        if (reader.getQName().equalsLocalName(Caption.ELEMENT)) {
            this.caption = new Caption();
            this.caption.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(Tr.ELEMENT)) {
            Tr tr;
            do {
                tr = new Tr();
                tr.read(reader);
                this.trs.add(tr);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(Tr.ELEMENT));
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
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }
}
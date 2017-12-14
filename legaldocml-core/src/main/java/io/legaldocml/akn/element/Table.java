package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.CoreReq;
import io.legaldocml.akn.attribute.TableAtts;
import io.legaldocml.akn.group.HTMLblock;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.BORDER;
import static io.legaldocml.akn.AknAttributes.CELLPADDING;
import static io.legaldocml.akn.AknAttributes.CELLSPACING;
import static io.legaldocml.akn.AknAttributes.WIDTH;
import static io.legaldocml.akn.AknElements.CAPTION;
import static io.legaldocml.akn.AknElements.TABLE;
import static io.legaldocml.akn.AknElements.TR;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4Integer;
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
public final class Table extends AbstractCore implements CoreReq, TableAtts, HTMLblock {

    /**
     * Memory address.
     */
    private static final long ADDRESS_TABLE = Buffers.address(TABLE);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(Blocksopt.ATTRIBUTES)
                .put(WIDTH, attributeGetterSetter4Integer(WIDTH, getFieldOffset(Table.class, "width")))
                .put(BORDER, attributeGetterSetter4Integer(WIDTH, getFieldOffset(Table.class, "border")))
                .put(CELLSPACING, attributeGetterSetter4Integer(CELLSPACING, getFieldOffset(Table.class, "cellspacing")))
                .put(CELLPADDING, attributeGetterSetter4Integer(CELLPADDING, getFieldOffset(Table.class, "cellpadding")))
                .build();
    }

    private final AknList<Tr> trs = new AknList<>(new Tr[4]);
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

    public void add(Tr tr) {
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
        writer.writeStart(ADDRESS_TABLE, 5);
        writeTableAtts(writer, this);
        CoreReq.super.write(writer);
        if (this.caption != null) {
            this.caption.write(writer);
        }
        this.trs.write(writer);
        writer.writeEnd(ADDRESS_TABLE, 5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        reader.nextStartOrEndElement();

        if (reader.getQName().equalsLocalName(CAPTION)) {
            this.caption = new Caption();
            this.caption.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(TR)) {
            Tr tr;
            do {
                tr = new Tr();
                tr.read(reader);
                this.trs.add(tr);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(TR));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return TABLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            if (this.caption != null) {
                this.caption.accept(visitor);
            }
            this.trs.accept(visitor);
            visitor.visitLeave(this);
        }
    }

}
package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.CellAttrs;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.COL_SPAN;
import static io.legaldocml.akn.AknAttributes.ROW_SPAN;
import static io.legaldocml.akn.AknElements.TD;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4Integer;
import static io.legaldocml.akn.util.XmlWriterHelper.writeCellAttrs;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element td is an HTML element and is used in Akoma Ntoso as in HTML, for a data cell of a table.
 * <p>
 * <pre>
 *   <xsd:element name="td">
 * 	   <xsd:complexType>
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="blocksopt">
 * 		     <xsd:attributeGroup ref="cellattrs"/>
 * 		   <xsd:extension>
 * 		 <xsd:complexContent>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Td extends Blocksopt implements CellAttrs, TrElement, SubFlowStructureElement {


    /**
     * Memory address.
     */
    private static final long ADDRESS_TD = Buffers.address(TD);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(Blocksopt.ATTRIBUTES)
                .put(COL_SPAN, attributeGetterSetter4Integer(COL_SPAN, getFieldOffset(Td.class, "colSpan")))
                .put(ROW_SPAN, attributeGetterSetter4Integer(ROW_SPAN, getFieldOffset(Td.class, "rowSpan")))
                .build();
    }

    private Integer rowSpan;
    private Integer colSpan;

    /**
     * {@inheritDoc}
     */
    public Integer getRowSpan() {
        return this.rowSpan;
    }

    /**
     * {@inheritDoc}
     */
    public void setRowSpan(Integer rowspan) {
        this.rowSpan = rowspan;
    }

    /**
     * {@inheritDoc}
     */
    public Integer getColSpan() {
        return this.colSpan;
    }

    /**
     * {@inheritDoc}
     */
    public void setColSpan(Integer colspan) {
        this.colSpan = colspan;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_TD, 2);
        writeCellAttrs(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_TD, 2);
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
    public String name() {
        return TD;
    }
}
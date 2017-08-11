package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.CellAttrs;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerInteger;
import static io.legaldocml.akn.util.XmlWriterHelper.writeCellAttrs;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element td is an HTML element and is used in Akoma Ntoso as in HTML, for a data cell of a table.
 * <p/>
 * <pre>
 *   <xsd:element name="td">
 * 	   <xsd:complexType>
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="blocksopt">
 * 		     <xsd:attributeGroup ref="cellattrs"/>
 * 		   </xsd:extension>
 * 		 </xsd:complexContent>
 * 	   </xsd:complexType>
 *   </xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Td extends Blocksopt implements CellAttrs, TrElement, SubFlowStructureElement {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "td";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(Blocksopt.ATTRIBUTES)
                .put(CellAttrs.ATTRIBUTE_COL_SPAN, biConsumerInteger(getFieldOffset(Td.class, "colSpan")))
                .put(CellAttrs.ATTRIBUTE_ROW_SPAN, biConsumerInteger(getFieldOffset(Td.class, "rowSpan")))
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
        writer.writeStart(ADDRESS, 2);
        writeCellAttrs(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS, 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }
}
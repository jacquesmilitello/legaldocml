package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.attribute.Name;
import io.legaldocml.akn.group.SubFlowElements;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.AknElements.SUB_FLOW;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.util.XmlWriterHelper.writeName;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element subFlow is a generic element for a subFlow.
 *
 * <pre>
 *   <xsd:element name="subFlow">
 * 	   <xsd:complexType>
 *       <xsd:complexContent>
 *         <xsd:extension base="subFlowStructure">
 *           <xsd:attributeGroup ref="name"/>
 *         <xsd:extension>
 *       <xsd:complexContent>
 *     <xsd:complexType>
 * 	 <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class SubFlow extends SubFlowStructure implements SubFlowElements, Name {

    /**
     * Memory address.
     */
    private static final long ADDRESS_SUB_FLOW = Buffers.address(SUB_FLOW);


    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(SubFlowStructure.ATTRIBUTES)
                .put(AknAttributes.NAME, biConsumerString(getFieldOffset(SubFlow.class, "name")))
                .build();
    }

    private String name;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_SUB_FLOW, 7);
        writeName(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_SUB_FLOW, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return SUB_FLOW;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }
}
package io.legaldocml.akn.element;


import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.attribute.Name;
import io.legaldocml.akn.group.BlockElements;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.AknElements.BLOCK;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.util.XmlWriterHelper.writeName;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;


/**
 * The element block is a generic element for a block. It can be placed in a container instead of any of the other
 * blocks. The attribute name is required and gives a name to the element.
 *
 * <pre>
 *   <xsd:element name="block">
 * 	   <xsd:complexType mixed="true">
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="inline">
 * 		     <xsd:attributeGroup ref="name" />
 * 		   <xsd:extension>
 * 	     <xsd:complexContent>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Block extends InlineType implements Name, BlockElements {

    /**
     * Memory address.
     */
    private static final long ADDRESS_BLOCK = Buffers.address(BLOCK);

    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(InlineType.ATTRIBUTES)
                .put(AknAttributes.NAME, biConsumerString(getFieldOffset(Block.class, "name")))
                .build();
    }

    /**
     * Name of this block.
     */
    private String name;

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_BLOCK, 5);
        writeName(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_BLOCK, 5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return BLOCK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }

}
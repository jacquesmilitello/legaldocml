package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.attribute.Froozen;
import io.legaldocml.util.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.AknElements.CONDITION;
import static io.legaldocml.akn.element.Attributes.biConsumerDateTime;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element condition is a metadata element specifying an open set of conditions on the modification (non managed by
 * Akoma Ntoso).
 *
 * <pre>
 *   <xsd:element name="condition" type="anyOtherType">
 * 	   <xsd:complexType>
 *       <xsd:complexContent>
 *         <xsd:extension base="anyOtherType">
 *           <xsd:attributeGroup ref="frozen"/>
 *         <xsd:extension>
 *       <xsd:complexContent>
 *     <xsd:complexType>
 *   <xsd:element>
 * <pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Condition extends AnyOtherType implements Froozen {

    /**
     * Memory address.
     */
    private static final long ADDRESS_CONDITION = Buffers.address(CONDITION);

    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(AnyOtherType.ATTRIBUTES)
                .put(AknAttributes.FROZEN, biConsumerDateTime(getFieldOffset(Condition.class, "frozen")))
                .build();
    }

    private Boolean frozen;

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean getFrozen() {
        return this.frozen;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFrozen(Boolean frozen) {
        this.frozen = frozen;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_CONDITION, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS_CONDITION, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return CONDITION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }
}
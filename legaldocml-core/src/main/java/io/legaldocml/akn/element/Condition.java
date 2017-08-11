package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Froozen;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerDateTime;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element condition is a metadata element specifying an open set of conditions on the modification (non managed by
 * Akoma Ntoso).
 * <p/>
 * <pre>
 *   <xsd:element name="condition" type="anyOtherType">
 * 	   <xsd:complexType>
 *       <xsd:complexContent>
 *         <xsd:extension base="anyOtherType">
 *           <xsd:attributeGroup ref="frozen"/>
 *         </xsd:extension>
 *       </xsd:complexContent>
 *     </xsd:complexType>
 *   </xsd:element>
 * <pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Condition extends AnyOtherType implements Froozen {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "condition";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(AnyOtherType.ATTRIBUTES)
                .put(Froozen.ATTRIBUTE, biConsumerDateTime(getFieldOffset(Condition.class, "frozen")))
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
        writer.writeStart(ADDRESS, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS, 9);
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
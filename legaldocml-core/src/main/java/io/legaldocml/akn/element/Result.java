package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.type.ResultType;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.AknElements.RESULT;
import static io.legaldocml.akn.element.Attributes.biConsumerEnum;
import static io.legaldocml.akn.util.XmlWriterHelper.writeResultType;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element result is a metadata element specifying the overall result of the judgment.
 *
 * <pre>
 *   <xsd:element name="result" type="anyOtherType">
 * 	   <xsd:complexType>
 *       <xsd:complexContent>
 *         <xsd:extension base="anyOtherType">
 *           <xsd:attributeGroup ref="resultType"/>
 *         <xsd:extension>
 *       <xsd:complexContent>
 *     <xsd:complexType>
 *   <xsd:element>
 * <pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Result extends AnyOtherType implements io.legaldocml.akn.attribute.ResultType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_RESULT = Buffers.address(RESULT);

    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(AnyOtherType.ATTRIBUTES)
                .put(AknAttributes.TYPE, biConsumerEnum(getFieldOffset(Result.class, "type"), ResultType.class))
                .build();
    }

    private ResultType type;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setType(ResultType type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultType getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_RESULT, 6);
        writeResultType(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_RESULT, 6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return RESULT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }

}
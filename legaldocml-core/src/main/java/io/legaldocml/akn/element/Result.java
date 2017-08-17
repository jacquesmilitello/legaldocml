package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.ResultType;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerEnum;
import static io.legaldocml.akn.util.XmlWriterHelper.writeResultType;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element result is a metadata element specifying the overall result of the judgment.
 *
 * <pre>
 *   &lt;xsd:element name="result" type="anyOtherType"&gt;
 * 	   &lt;xsd:complexType&gt;
 *       &lt;xsd:complexContent&gt;
 *         &lt;xsd:extension base="anyOtherType"&gt;
 *           &lt;xsd:attributeGroup ref="resultType"/&gt;
 *         &lt;xsd:extension&gt;
 *       &lt;xsd:complexContent&gt;
 *     &lt;xsd:complexType&gt;
 *   &lt;xsd:element&gt;
 * <pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Result extends AnyOtherType implements io.legaldocml.akn.attribute.ResultType {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "result";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(AnyOtherType.ATTRIBUTES)
                .put(io.legaldocml.akn.attribute.ResultType.ATTRIBUTE, biConsumerEnum(getFieldOffset(Result.class, "type"), ResultType.class))
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
        writer.writeStart(ADDRESS, 6);
        writeResultType(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS, 6);
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
package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.BooleanValue;
import io.legaldocml.akn.util.XmlWriterHelper;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerBoolean;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The type booleanValueType specifies a boolean value attribute to FRBR elements.
 *
 * <pre>
 *   <xsd:complexType name="booleanValueType">
 *     <xsd:complexContent>
 *       <xsd:extension base="metaopt">
 *         <xsd:attributeGroup ref="booleanvalue"/>
 *       <xsd:extension>
 *     <xsd:complexContent>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class BooleanValueType extends MetaOpt implements BooleanValue {

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(MetaOpt.ATTRIBUTES)
                .put(BooleanValue.ATTRIBUTE, biConsumerBoolean(getFieldOffset(BooleanValueType.class, "value")))
                .build();
    }

    private Boolean value;

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean getValue() {
        return this.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(Boolean value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        super.write(writer);
        XmlWriterHelper.writeBooleanValue(writer, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }

}
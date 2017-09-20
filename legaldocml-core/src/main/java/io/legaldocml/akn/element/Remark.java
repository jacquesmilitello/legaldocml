package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.group.ANinline;
import io.legaldocml.akn.type.RemarkType;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.unsafe.UnsafeString;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.AknElements.REMARK;
import static io.legaldocml.akn.element.Attributes.biConsumerEnum;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * the element remark is an inline element for the specification of editorial remarks (e.g., applauses, laughters, etc.)
 * especially within debate records/
 *
 * <pre>
 *   <xsd:element name="remark">
 * 	   <xsd:complexType mixed="true">
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="inline">
 * 		     <xsd:attributeGroup ref="remarkType"/>
 * 		   <xsd:extension>
 * 	     <xsd:complexContent>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Remark extends InlineType implements io.legaldocml.akn.attribute.RemarkType, ANinline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_REMARK = Buffers.address(REMARK);

    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(InlineType.ATTRIBUTES)
                .put(AknAttributes.TYPE, biConsumerEnum(getFieldOffset(Remark.class, "type"), RemarkType.class))
                .build();
    }


    private RemarkType type;

    /**
     * {@inheritDoc}
     */
    @Override
    public RemarkType getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setType(RemarkType type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_REMARK, 6);
        if (this.type != null) {
            writer.writeAttribute(Attributes.ADDRESS_TYPE, 4, UnsafeString.getChars(this.type.name()));
        }
        super.write(writer);
        writer.writeEnd(ADDRESS_REMARK, 6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return REMARK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }

}
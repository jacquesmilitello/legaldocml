package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.group.ANinline;
import io.legaldocml.akn.type.RemarkType;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;
import io.legaldocml.unsafe.UnsafeString;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.TYPE;
import static io.legaldocml.akn.AknElements.REMARK;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4Enum;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * the element remark is an inline element for the specification of editorial remarks (e.g., applauses, laughters, etc.)
 * especially within debate records/
 * <p>
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

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(InlineType.ATTRIBUTES)
                .put(TYPE, attributeGetterSetter4Enum(TYPE, getFieldOffset(Remark.class, "type"), RemarkType.class))
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
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }

}
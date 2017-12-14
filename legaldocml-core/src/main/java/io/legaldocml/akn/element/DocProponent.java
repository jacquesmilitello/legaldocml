package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.group.ANtitleInline;
import io.legaldocml.akn.type.RoleRef;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.AS;
import static io.legaldocml.akn.AknElements.DOC_PROPONENT;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4RoleRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRole;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * the element docProponent is an inline element within preface to identify the string used by the document for its
 * proponent.
 *
 * <pre>
 *   <xsd:complexType mixed="true">
 *     <xsd:complexContent>
 *       <xsd:extension base="inline">
 *         <xsd:attributeGroup ref="role"/>
 *       <xsd:extension>
 *     <xsd:complexContent>
 *   <xsd:complex>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DocProponent extends InlineType implements ANtitleInline, io.legaldocml.akn.attribute.Role {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DOC_PROPONENT = Buffers.address(DOC_PROPONENT);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(InlineType.ATTRIBUTES)
                .put(AS, attributeGetterSetter4RoleRef(AS, getFieldOffset(DocProponent.class, "as")))
                .build();
    }

    private RoleRef as;

    /**
     * {@inheritDoc}
     */
    @Override
    public RoleRef getAs() {
        return this.as;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAs(RoleRef as) {
        this.as = as;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DOC_PROPONENT, 12);
        writeRole(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_DOC_PROPONENT, 12);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DOC_PROPONENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            super.accept(visitor);
            visitor.visitLeave(this);
        }
    }

}
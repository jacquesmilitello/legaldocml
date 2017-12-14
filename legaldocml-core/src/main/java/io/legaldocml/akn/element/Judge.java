package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Role;
import io.legaldocml.akn.group.ANheaderInline;
import io.legaldocml.akn.type.RoleRef;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.JUDGE;
import static io.legaldocml.akn.element.Attributes.biConsumerRoleRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRole;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element judge is an inline element within judgments to identify where the document defines one of the judges, and
 * his/her role.
 * <p>
 * <pre>
 * 	 <xsd:element name="judge">
 * 	   <xsd:complexType mixed="true">
 *       <xsd:complexContent>
 *         <xsd:extension base="inlinereqreq">
 *           <xsd:attributeGroup ref="role"/>
 *         <xsd:extension>
 *       <xsd:complexContent>
 *     <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Judge extends InlineReqReqType implements Role, ANheaderInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_JUDGE = Buffers.address(JUDGE);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(InlineReqReqType.ATTRIBUTES)
                .put(AknAttributes.AS, biConsumerRoleRef(AknAttributes.AS, getFieldOffset(Judge.class, "as")))
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
        writer.writeStart(ADDRESS_JUDGE, 5);
        writeRole(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_JUDGE, 5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return JUDGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }
}
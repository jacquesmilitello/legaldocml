package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.attribute.LawyerAtts;
import io.legaldocml.akn.group.ANheaderInline;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.RoleRef;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerRoleRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writeLawyerAtts;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element lawyer is an inline element within judgments to identify where the document defines one of the lawyers,
 * his/her role, which party it represents, and the other lawyer, if any, this lawyer received the power delegation of
 * power in some role.
 *
 * <pre>
 * 	 <xsd:element name="lawyer">
 * 	   <xsd:complexType mixed="true">
 *       <xsd:complexContent>
 *         <xsd:extension base="inlinereqreq">
 *           <xsd:attributeGroup ref="lawyerAtts"/>
 *         <xsd:extension>
 *       <xsd:complexContent>
 *     <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Lawyer extends InlineReqReqType implements LawyerAtts, ANheaderInline {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "lawyer";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(InlineReqReqType.ATTRIBUTES)
                .put(AknAttributes.AS, biConsumerRoleRef(getFieldOffset(Lawyer.class, "as")))
                .put(AknAttributes.FOR, biConsumerRoleRef(getFieldOffset(Lawyer.class, "_for")))
                .put(AknAttributes.EMPOWERED_BY, biConsumerRoleRef(getFieldOffset(Lawyer.class, "empoweredBy")))
                .build();
    }

    private RoleRef as;
    private AgentRef _for;
    private AgentRef empoweredBy;

    /**
     * {@inheritDoc}
     */
    @Override
    public AgentRef getFor() {
        return this._for;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFor(AgentRef for_) {
        this._for = for_;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AgentRef getEmpoweredBy() {
        return this.empoweredBy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEmpoweredBy(AgentRef empoweredBy) {
        this.empoweredBy = empoweredBy;
    }


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
        writer.writeStart(ADDRESS, 6);
        writeLawyerAtts(writer, this);
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
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }
}
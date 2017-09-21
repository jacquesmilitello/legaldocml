package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.attribute.MappingAtts;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.EidRef;
import io.legaldocml.akn.type.EventRefRef;
import io.legaldocml.akn.type.RoleRef;
import io.legaldocml.akn.type.WidRef;
import io.legaldocml.util.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.AknElements.MAPPING;
import static io.legaldocml.akn.element.Attributes.biConsumerAgentRef;
import static io.legaldocml.akn.element.Attributes.biConsumerEidRef;
import static io.legaldocml.akn.element.Attributes.biConsumerEventRefRef;
import static io.legaldocml.akn.element.Attributes.biConsumerRoleRef;
import static io.legaldocml.akn.element.Attributes.biConsumerWidRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writeMappingAtts;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element mapping contains a reference to the permanent wId (attribute original) of a structure, and to the eId
 * (attribute current) such structure had during the time interval included between an initial temporalGroup and a final
 * temporalGroup. This is useful for tracking the evolving ids of documents frequently renumbered (e,g., bills). Every
 * single element whose wId does not match its eId needs to be represented here.
 *
 *
 * <pre>
 *   <xsd:element name="mapping">
 *     <xsd:complexType name="valueType">
 *       <xsd:complexContent>
 *         <xsd:extension base="metareq">
 *           <xsd:attributeGroup ref="mappingAtts"/>
 *         <xsd:extension>
 *       <xsd:complexContent>
 *     <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Mapping extends MetaReq implements MappingAtts {

    /**
     * Memory address.
     */
    private static final long ADDRESS_MAPPING = Buffers.address(MAPPING);

    protected static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(MetaReq.ATTRIBUTES)
                .put(AknAttributes.ORIGINAL, biConsumerWidRef(getFieldOffset(Mapping.class, "original")))
                .put(AknAttributes.CURRENT, biConsumerEidRef(getFieldOffset(Mapping.class, "current")))
                .put(AknAttributes.START, biConsumerEventRefRef(getFieldOffset(Mapping.class, "start")))
                .put(AknAttributes.END, biConsumerEventRefRef(getFieldOffset(Mapping.class, "end")))
                .put(AknAttributes.AS, biConsumerRoleRef(getFieldOffset(Mapping.class, "as")))
                .put(AknAttributes.BY, biConsumerAgentRef(getFieldOffset(Mapping.class, "by")))
                .build();
    }


    private WidRef original;
    private EidRef current;
    private EventRefRef start;
    private EventRefRef end;
    private RoleRef as;
    private AgentRef by;

    /**
     * {@inheritDoc}
     */
    @Override
    public WidRef getOriginal() {
        return this.original;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOriginal(WidRef original) {
        this.original = original;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EidRef getCurrent() {
        return this.current;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCurrent(EidRef current) {
        this.current = current;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EventRefRef getStart() {
        return this.start;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStart(EventRefRef start) {
        this.start = start;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EventRefRef getEnd() {
        return this.end;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEnd(EventRefRef end) {
        this.end = end;
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
    public AgentRef getBy() {
        return this.by;
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
    public void setBy(AgentRef by) {
        this.by = by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_MAPPING, 8);
        writeMappingAtts(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_MAPPING, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return MAPPING;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }

}
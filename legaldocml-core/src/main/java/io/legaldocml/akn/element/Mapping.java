package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Agent;
import io.legaldocml.akn.attribute.MappingAtts;
import io.legaldocml.akn.attribute.Role;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.EidRef;
import io.legaldocml.akn.type.EventRefRef;
import io.legaldocml.akn.type.RoleRef;
import io.legaldocml.akn.type.WidRef;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;

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
     * XML tag element name.
     */
    public static final String ELEMENT = "mapping";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    protected static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(MetaReq.ATTRIBUTES)
                .put(MappingAtts.ATTRIBUTE_ORIGINAL, biConsumerWidRef(getFieldOffset(Mapping.class, "original")))
                .put(MappingAtts.ATTRIBUTE_CURRENT, biConsumerEidRef(getFieldOffset(Mapping.class, "current")))
                .put(MappingAtts.ATTRIBUTE_START, biConsumerEventRefRef(getFieldOffset(Mapping.class, "start")))
                .put(MappingAtts.ATTRIBUTE_END, biConsumerEventRefRef(getFieldOffset(Mapping.class, "end")))
                .put(Role.ATTRIBUTE, biConsumerRoleRef(getFieldOffset(Mapping.class, "as")))
                .put(Agent.ATTRIBUTE, biConsumerAgentRef(getFieldOffset(Mapping.class, "by")))
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
        writer.writeStart(ADDRESS, 8);
        writeMappingAtts(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS, 8);
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
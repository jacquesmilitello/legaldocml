package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.MappingAtts;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.EidRef;
import io.legaldocml.akn.type.EventRefRef;
import io.legaldocml.akn.type.RoleRef;
import io.legaldocml.akn.type.WidRef;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.AS;
import static io.legaldocml.akn.AknAttributes.BY;
import static io.legaldocml.akn.AknAttributes.CURRENT;
import static io.legaldocml.akn.AknAttributes.END;
import static io.legaldocml.akn.AknAttributes.ORIGINAL;
import static io.legaldocml.akn.AknAttributes.START;
import static io.legaldocml.akn.AknElements.MAPPING;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4AgentRef;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4EidRef;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4EventRefRef;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4RoleRef;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4WidRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writeMappingAtts;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element mapping contains a reference to the permanent wId (attribute original) of a structure, and to the eId
 * (attribute current) such structure had during the time interval included between an initial temporalGroup and a final
 * temporalGroup. This is useful for tracking the evolving ids of documents frequently renumbered (e,g., bills). Every
 * single element whose wId does not match its eId needs to be represented here.
 * <p>
 * <p>
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

    protected static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(MetaReq.ATTRIBUTES)
                .put(ORIGINAL, attributeGetterSetter4WidRef(ORIGINAL, getFieldOffset(Mapping.class, "original")))
                .put(CURRENT, attributeGetterSetter4EidRef(CURRENT, getFieldOffset(Mapping.class, "current")))
                .put(START, attributeGetterSetter4EventRefRef(START, getFieldOffset(Mapping.class, "start")))
                .put(END, attributeGetterSetter4EventRefRef(END, getFieldOffset(Mapping.class, "end")))
                .put(AS, attributeGetterSetter4RoleRef(AS, getFieldOffset(Mapping.class, "as")))
                .put(BY, attributeGetterSetter4AgentRef(BY, getFieldOffset(Mapping.class, "by")))
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
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }

}
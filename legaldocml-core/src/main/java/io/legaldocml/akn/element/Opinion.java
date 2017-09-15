package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.group.ANinline;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.OpinionType;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.AknElements.OPINION;
import static io.legaldocml.akn.element.Attributes.biConsumerAgentRef;
import static io.legaldocml.akn.element.Attributes.biConsumerEnum;
import static io.legaldocml.akn.util.XmlWriterHelper.writeOpinionType;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element opinion is an inline element to identify where the document defines the opinion of an actor.
 *
 * <pre>
 *   <xsd:element name="opinion">
 * 	   <xsd:complexType mixed="true">
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="inline">
 * 		     <xsd:attributeGroup ref="opinionType"/>
 * 		   <xsd:extension>
 * 	     <xsd:complexContent>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Opinion extends InlineType implements io.legaldocml.akn.attribute.OpinionType, ANinline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_OPINION = Buffers.address(OPINION);

    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(InlineType.ATTRIBUTES)
                .put(AknAttributes.TYPE, biConsumerEnum(getFieldOffset(Opinion.class, "type"), OpinionType.class))
                .put(AknAttributes.BY, biConsumerAgentRef(getFieldOffset(Opinion.class, "by")))
                .build();
    }

    private OpinionType type;
    private AgentRef by;

    /**
     * {@inheritDoc}
     */
    @Override
    public OpinionType getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setType(OpinionType type) {
        this.type = type;
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
    public void setBy(AgentRef by) {
        this.by = by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_OPINION, 7);
        writeOpinionType(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_OPINION, 7);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return OPINION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }

}
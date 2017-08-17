package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Agent;
import io.legaldocml.akn.group.ANinline;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.OpinionType;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerAgentRef;
import static io.legaldocml.akn.element.Attributes.biConsumerEnum;
import static io.legaldocml.akn.util.XmlWriterHelper.writeOpinionType;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element opinion is an inline element to identify where the document defines the opinion of an actor.
 *
 * <pre>
 *   &lt;xsd:element name="opinion"&gt;
 * 	   &lt;xsd:complexType mixed="true"&gt;
 * 	     &lt;xsd:complexContent&gt;
 * 		   &lt;xsd:extension base="inline"&gt;
 * 		     &lt;xsd:attributeGroup ref="opinionType"/&gt;
 * 		   &lt;xsd:extension&gt;
 * 	     &lt;xsd:complexContent&gt;
 * 	   &lt;xsd:complexType&gt;
 *   &lt;xsd:element&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Opinion extends InlineType implements io.legaldocml.akn.attribute.OpinionType, ANinline {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "opinion";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(InlineType.ATTRIBUTES)
                .put(io.legaldocml.akn.attribute.OpinionType.ATTRIBUTE, biConsumerEnum(getFieldOffset(Opinion.class, "type"), OpinionType.class))
                .put(Agent.ATTRIBUTE, biConsumerAgentRef(getFieldOffset(Opinion.class, "by")))
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
        writer.writeStart(ADDRESS, 7);
        writeOpinionType(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS, 7);
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
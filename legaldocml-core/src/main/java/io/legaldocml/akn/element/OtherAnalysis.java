package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Source;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.util.XmlWriterHelper;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerAgentRef;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element otherAnalysis is a metadata container of any additional metadata analysis element that does not belong to
 * the specific categories provided before. Anything can be placed in this element.
 * <p/>
 * <pre>
 *   <xsd:complexType>
 *     <xsd:complexContent>
 *       <xsd:extension base="anyOtherType">
 *         <xsd:attributeGroup ref="source"/>
 *       </xsd:extension>
 *     </xsd:complexContent>
 *   </xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class OtherAnalysis extends AnyOtherType implements Source {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "otherAnalysis";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;


    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(AnyOtherType.ATTRIBUTES)
                .put("source", biConsumerAgentRef(getFieldOffset(OtherAnalysis.class, "source")))
                .build();
    }

    private AgentRef source;

    /**
     * {@inheritDoc}
     */
    public AgentRef getSource() {
        return this.source;
    }

    /**
     * {@inheritDoc}
     */
    public void setSource(AgentRef source) {
        this.source = source;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 13);
        XmlWriterHelper.writeSource(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS, 13);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}
package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Source;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.SOURCE;
import static io.legaldocml.akn.AknElements.PRESENTATION;
import static io.legaldocml.akn.element.Attributes.biConsumerAgentRef;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element presentation is a metadata container of any presentation specification for the visual rendering of Akoam
 * Ntoso elements. Anything can be placed in this element.
 * <p>
 * <pre>
 *   <xsd:element name="presentation">
 *     <xsd:complexType>
 *       <xsd:complexContent>
 *         <xsd:extension base="anyOtherType">
 *           <xsd:attributeGroup ref="source"/>
 *         <xsd:extension>
 *       <xsd:complexContent>
 *     <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Presentation extends AnyOtherType implements Source {

    /**
     * Memory address.
     */
    private static final long ADDRESS_PRESENTATION = Buffers.address(PRESENTATION);


    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .put(SOURCE, biConsumerAgentRef(SOURCE, getFieldOffset(Presentation.class, "source")))
                .build();
    }

    private AgentRef source;

    /**
     * {@inheritDoc}
     */
    @Override
    public AgentRef getSource() {
        return this.source;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSource(AgentRef source) {
        this.source = source;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_PRESENTATION, 12);
        super.write(writer);
        writer.writeEnd(ADDRESS_PRESENTATION, 12);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return PRESENTATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }
}
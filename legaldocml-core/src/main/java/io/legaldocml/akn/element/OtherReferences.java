package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Source;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.AknAttributes.SOURCE;
import static io.legaldocml.akn.AknElements.ALTERNATIVE_REFERENCE;
import static io.legaldocml.akn.AknElements.IMPLICIT_REFERENCE;
import static io.legaldocml.akn.AknElements.OTHER_REFERENCES;
import static io.legaldocml.akn.element.Attributes.ATTRIBUTE_CONSUMER;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4AgentRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writeSource;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * <pre>
 *   <xsd:element name="mappings">
 *     <xsd:complexType>
 *       <xsd:choice minOccurs="1" maxOccurs="unbounded">
 *         <xsd:element ref="implicitReference"/>
 *         <xsd:element ref="alternativeReference"/>
 *       <xsd:choice>
 *       <xsd:attributeGroup ref="source"/>
 *     <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class OtherReferences implements Source {

    /**
     * Memory address.
     */
    private static final long ADDRESS_OTHER_REFERENCES = Buffers.address(OTHER_REFERENCES);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    private static final ImmutableMap<String, Supplier<OtherReferencesElement>> ELEMS;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .put(SOURCE, attributeGetterSetter4AgentRef(SOURCE, getFieldOffset(OtherReferences.class, "source")))
                .build();

        ELEMS = ImmutableMap.<String, Supplier<OtherReferencesElement>>builder()
                .put(ALTERNATIVE_REFERENCE, AlternativeReference::new)
                .put(IMPLICIT_REFERENCE, ImplicitReference::new)
                .build();
    }

    // Mandatory (min 1)
    private final AknList<OtherReferencesElement> elements = new AknList<>(new OtherReferencesElement[2]);

    // Mandatory
    private AgentRef source;

    private AknObject parent;

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
        writer.writeStart(ADDRESS_OTHER_REFERENCES, 15);
        writeSource(writer, this);
        this.elements.write(writer);
        writer.writeEnd(ADDRESS_OTHER_REFERENCES, 15);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        reader.forEach(this, ATTRIBUTE_CONSUMER);
        XmlReaderHelper.read(reader, this, this.elements, ELEMS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return OTHER_REFERENCES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }

    @SuppressWarnings("unchecked")
    public <T extends AknObject> T getParent() {
        return (T)parent;
    }

    public <T extends AknObject> void setParent(T parent) {
        this.parent = parent;
    }
}
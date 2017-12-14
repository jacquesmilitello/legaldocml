package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Source;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.SOURCE;
import static io.legaldocml.akn.AknElements.CLASSIFICATION;
import static io.legaldocml.akn.AknElements.KEYWORD;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4AgentRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writeSource;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * <pre>
 *   <xsd:element name="classification">
 * 	   <xsd:complexType>
 * 	     <xsd:sequence>
 *   	   <xsd:element ref="keyword" minOccurs="1" maxOccurs="unbounded"/>
 * 		 <xsd:sequence>
 * 	     <xsd:attributeGroup ref="source"/>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Classification implements Source {

    /**
     * Memory address.
     */
    private static final long ADDRESS_CLASSIFICATION = Buffers.address(CLASSIFICATION);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .put(SOURCE, attributeGetterSetter4AgentRef(SOURCE, getFieldOffset(Classification.class, "source")))
                .build();
    }

    private final AknList<Keyword> keywords = new AknList<>(new Keyword[4]);

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
    public void read(XmlReader reader) {
        Attributes.read(reader, this);
        reader.nextStartOrEndElement();

        if (reader.getQName().equalsLocalName(KEYWORD)) {
            Keyword keyword;
            do {
                keyword = new Keyword();
                keyword.read(reader);
                this.keywords.add(keyword);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(KEYWORD));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_CLASSIFICATION, 14);
        writeSource(writer, this);
        this.keywords.write(writer);
        writer.writeEnd(ADDRESS_CLASSIFICATION, 14);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return CLASSIFICATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            this.keywords.accept(visitor);
            visitor.visitLeave(this);
        }
    }
}
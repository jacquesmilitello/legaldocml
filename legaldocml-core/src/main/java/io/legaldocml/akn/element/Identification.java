package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Source;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;
import io.legaldocml.util.ToStringBuilder;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.SOURCE;
import static io.legaldocml.akn.AknElements.FRBR_EXPRESSION;
import static io.legaldocml.akn.AknElements.FRBR_ITEM;
import static io.legaldocml.akn.AknElements.FRBR_MANIFESTATION;
import static io.legaldocml.akn.AknElements.FRBR_WORK;
import static io.legaldocml.akn.AknElements.IDENTIFICATION;
import static io.legaldocml.akn.element.Attributes.ATTRIBUTE_CONSUMER;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4AgentRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writeSource;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * <pre>
 *   <xsd:element name="identification">
 * 	   <xsd:complexType>
 * 	     <xsd:sequence>
 * 		   <xsd:element ref="FRBRWork"/>
 * 		   <xsd:element ref="FRBRExpression"/>
 * 		   <xsd:element ref="FRBRManifestation"/>
 * 		   <xsd:element ref="FRBRItem" minOccurs="0" maxOccurs="1"/>
 * 		 <xsd:sequence>
 * 	   <xsd:attributeGroup ref="source"/>
 *     <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Identification implements AknObject, Source {


    private static final long ADDRESS_IDENTIFICATION = Buffers.address(IDENTIFICATION);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .put(SOURCE, attributeGetterSetter4AgentRef(SOURCE, getFieldOffset(Identification.class, "source")))
                .build();
    }

    // Mandatory
    private final FRBRWork work = new FRBRWork();

    // Mandatory
    private final FRBRExpression expression = new FRBRExpression();

    // Mandatory
    private final FRBRManifestation manifestation = new FRBRManifestation();

    // Optional
    private FRBRItem item;

    // Mandatory
    private AgentRef source;

    private AknObject parent;

    public FRBRWork getFRBRWork() {
        return this.work;
    }

    public FRBRExpression getFRBRExpression() {
        return this.expression;
    }

    public FRBRManifestation getFRBRManifestation() {
        return this.manifestation;
    }

    public FRBRItem getFRBRItem() {
        return this.item;
    }

    public void setFRBRItem(FRBRItem fRBRItem) {
        this.item = fRBRItem;
    }


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
    public String name() {
        return IDENTIFICATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_IDENTIFICATION, 14);
        writeSource(writer, this);
        this.work.write(writer);
        this.expression.write(writer);
        this.manifestation.write(writer);
        if (this.item != null) {
            this.item.write(writer);
        }
        writer.writeEnd(ADDRESS_IDENTIFICATION, 14);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {

        reader.forEach(this, ATTRIBUTE_CONSUMER);

        reader.nextStartOrEndElement();

        if (reader.getQName().equalsLocalName(FRBR_WORK)) {
            this.work.read(reader);
        }

        if (reader.getQName().equalsLocalName(FRBR_EXPRESSION)) {
            this.expression.read(reader);
        }

        if (reader.getQName().equalsLocalName(FRBR_MANIFESTATION)) {
            this.manifestation.read(reader);
        }

        if (reader.getQName().equalsLocalName(FRBR_ITEM)) {
            this.item = new FRBRItem();
            this.item.read(reader);
        }

        if (reader.getQName().equalsLocalName(IDENTIFICATION)) {
            reader.nextStartOrEndElement();
        } else {
            throw new IllegalStateException();
        }
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
            this.work.accept(visitor);
            this.expression.accept(visitor);
            this.manifestation.accept(visitor);
            if (this.item != null) {
                this.item.accept(visitor);
            }
            visitor.visitLeave(this);
        }
    }
    
	/**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, false);
        builder.append(SOURCE, this.source);
        return builder.toString();
    }

    @SuppressWarnings("unchecked")
    public <T extends AknObject> T getParent() {
        return (T)parent;
    }

    public <T extends AknObject> void setParent(T parent) {
        this.parent = parent;
    }

}
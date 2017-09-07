package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Source;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.AknElements.IDENTIFICATION;
import static io.legaldocml.akn.element.Attributes.biConsumerAgentRef;
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

    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .put(AknAttributes.SOURCE, biConsumerAgentRef(getFieldOffset(Identification.class, "source")))
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

        Attributes.read(reader, this);

        reader.nextStartOrEndElement();

        if (reader.getQName().equalsLocalName(FRBRWork.ELEMENT)) {
            this.work.read(reader);
        }

        if (reader.getQName().equalsLocalName(FRBRExpression.ELEMENT)) {
            this.expression.read(reader);
        }

        if (reader.getQName().equalsLocalName(FRBRManifestation.ELEMENT)) {
            this.manifestation.read(reader);
        }

        if (reader.getQName().equalsLocalName(FRBRItem.ELEMENT)) {
            this.item = new FRBRItem();
            this.item.read(reader);
        }

        if (reader.getQName().equalsLocalName(IDENTIFICATION)) {
            reader.nextStartOrEndElement();
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }

}
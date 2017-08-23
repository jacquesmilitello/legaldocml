package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Source;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;

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

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "identification";

    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .put(Source.ATTRIBUTE, biConsumerAgentRef(getFieldOffset(Identification.class, "source")))
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
        return ELEMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 14);
        writeSource(writer, this);
        this.work.write(writer);
        this.expression.write(writer);
        this.manifestation.write(writer);
        if (this.item != null) {
            this.item.write(writer);
        }
        writer.writeEnd(ADDRESS, 14);

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

        if (reader.getQName().equalsLocalName(ELEMENT)) {
            reader.nextStartOrEndElement();
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }

}
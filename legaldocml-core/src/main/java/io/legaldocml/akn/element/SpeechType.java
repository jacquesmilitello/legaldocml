package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.CoreOpt;
import io.legaldocml.akn.attribute.SpeechAtts;
import io.legaldocml.akn.group.BlockElements;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.RoleRef;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.util.XmlWriterHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.function.Supplier;

import static io.legaldocml.akn.AknAttributes.AS;
import static io.legaldocml.akn.AknAttributes.BY;
import static io.legaldocml.akn.AknAttributes.END_TIME;
import static io.legaldocml.akn.AknAttributes.START_TIME;
import static io.legaldocml.akn.AknAttributes.TO;
import static io.legaldocml.akn.AknElements.FROM;
import static io.legaldocml.akn.element.Attributes.biConsumerAgentRef;
import static io.legaldocml.akn.element.Attributes.biConsumerDateTime;
import static io.legaldocml.akn.element.Attributes.biConsumerRoleRef;
import static io.legaldocml.akn.element.Groups.blockElements;
import static io.legaldocml.akn.element.Groups.convertSuper;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The complex type speechType defines the content model and attributes shared by all speech elements. Here the eId
 * attribute is optional.
 * <p>
 * <pre>
 *   <xsd:complexType name="speechType">
 * 	   <xsd:complexContent>
 * 	     <xsd:extension base="basehierarchy">
 * 		   <xsd:sequence>
 * 		     <xsd:element ref="from" minOccurs="0" maxOccurs="1"/>
 * 			 <xsd:sequence minOccurs="1" maxOccurs="unbounded">
 * 		       <xsd:group ref="blockElements"/>
 * 			 <xsd:sequence>
 * 		   <xsd:sequence>
 * 		   <xsd:attributeGroup ref="coreopt"/>
 * 		   <xsd:attributeGroup ref="speechAtts"/>
 * 	     <xsd:extension>
 * 	   <xsd:complexContent>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class SpeechType extends BaseHierarchy implements CoreOpt, SpeechAtts {

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    private static final ImmutableMap<String, Supplier<BlockElements>> ELEMS;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(AltHierarchy.ATTRIBUTES)
                .put(BY, biConsumerAgentRef(BY, getFieldOffset(SpeechType.class, "by")))
                .put(AS, biConsumerRoleRef(AS, getFieldOffset(SpeechType.class, "as")))
                .put(TO, biConsumerAgentRef(TO, getFieldOffset(SpeechType.class, "to")))
                .put(START_TIME, biConsumerDateTime(START_TIME, getFieldOffset(SpeechType.class, "startTime")))
                .put(END_TIME, biConsumerDateTime(END_TIME, getFieldOffset(SpeechType.class, "endTime")))
                .build();

        ELEMS = ImmutableMap.<String, Supplier<BlockElements>>builder()
                .putAll(convertSuper(blockElements()))
                .build();
    }

    private final AknList<BlockElements> elements = new AknList<>(new BlockElements[4]);
    private AgentRef by;
    private RoleRef as;
    private AgentRef to;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private From from;

    /**
     * {@inheritDoc}
     */
    public AgentRef getBy() {
        return this.by;
    }

    /**
     * {@inheritDoc}
     */
    public void setBy(AgentRef by) {
        this.by = by;
    }

    /**
     * {@inheritDoc}
     */
    public RoleRef getAs() {
        return this.as;
    }

    /**
     * {@inheritDoc}
     */
    public void setAs(RoleRef as) {
        this.as = as;
    }

    /**
     * {@inheritDoc}
     */
    public AgentRef getTo() {
        return this.to;
    }

    /**
     * {@inheritDoc}
     */
    public void setTo(AgentRef to) {
        this.to = to;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public From getFrom() {
        return this.from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        XmlWriterHelper.writeSpeechAtts(writer, this);
        CoreOpt.super.write(writer);
        super.write(writer);
        if (this.from != null) {
            this.from.write(writer);
        }
        this.elements.write(writer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        QName qName = reader.getQName();
        super.read(reader);
        if (reader.getQName().equalsLocalName(FROM)) {
            this.from = new From();
            this.from.read(reader);
            reader.nextStartOrEndElement();
        }
        XmlReaderHelper.read(reader, this.elements, ELEMS, qName);
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
        super.accept(visitor);
        this.elements.accept(visitor);
    }
}

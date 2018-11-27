package io.legaldocml.xliff.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;
import io.legaldocml.util.ToStringBuilder;
import io.legaldocml.xliff.attribute.CanResegment;
import io.legaldocml.xliff.attribute.Id;
import io.legaldocml.xliff.type.StateType;
import io.legaldocml.xliff.type.YesNo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4Enum;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;
import static io.legaldocml.xliff.element.XliffAttributes.ATTRIBUTE_CONSUMER;
import static io.legaldocml.xliff.element.XliffAttributes.CAN_RESEGMENT;
import static io.legaldocml.xliff.element.XliffAttributes.ID;
import static io.legaldocml.xliff.element.XliffAttributes.STATE;
import static io.legaldocml.xliff.element.XliffAttributes.SUB_STATE;
import static io.legaldocml.xliff.element.XliffElements.SEGMENT;
import static io.legaldocml.xliff.element.XliffElements.SOURCE;

/**
 * <pre>
 *   <xs:element name="segment">
 *     <xs:complexType mixed="false">
 *       <xs:sequence>
 *         <xs:element minOccurs="1" maxOccurs="1" ref="xlf:source"/>
 *         <xs:element minOccurs="0" maxOccurs="1" ref="xlf:target"/>
 *       </xs:sequence>
 *       <xs:attribute name="id" use="optional" type="xs:NMTOKEN"/>
 *       <xs:attribute name="canResegment" use="optional" type="xlf:yesNo"/>
 *       <xs:attribute name="state" use="optional" type="xlf:stateType" default="initial"/>
 *       <xs:attribute name="subState" use="optional"/>
 *     </xs:complexType>
 *   </xs:element>
 * </pre>
 */
public final class Segment implements UnitElement, Id, CanResegment {

    /**
     * SLF4J logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Segment.class);

    private static final ImmutableMap<String, AttributeGetterSetter<XliffObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<XliffObject>>builder()
                .put(ID, attributeGetterSetter4String(ID, getFieldOffset(Segment.class, "id")))
                .put(CAN_RESEGMENT, attributeGetterSetter4String(CAN_RESEGMENT, getFieldOffset(Segment.class, "canResegment")))
                .put(STATE, attributeGetterSetter4Enum(STATE, getFieldOffset(Segment.class, "state"), StateType.FUNCTION))
                .put(SUB_STATE, attributeGetterSetter4String(SUB_STATE, getFieldOffset(Segment.class, "subState")))
                .build();
    }

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(SEGMENT);

    private String id;
    private YesNo canResegment;
    private StateType state;
    private String subState;

    private final Source source = new Source();

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public YesNo getCanResegment() {
        return this.canResegment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCanResegment(YesNo canResegment) {
        this.canResegment = canResegment;
    }


    public StateType getState() {
        return state;
    }

    public void setState(StateType state) {
        this.state = state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 7);
        Id.super.write(writer);
        this.source.write(writer);
        writer.writeEnd(ADDRESS,7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {

        reader.forEach(this, ATTRIBUTE_CONSUMER);
        reader.nextStartOrEndElement();

        if (reader.getQName().equalsLocalName(SOURCE)) {
            this.source.read(reader);
        } else {
            throw new RuntimeException();
        }



    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, false);
        return builder.toString();
    }

    @Override
    public ImmutableMap<String, AttributeGetterSetter<XliffObject>> attributes() {
        return ATTRIBUTES;
    }
}
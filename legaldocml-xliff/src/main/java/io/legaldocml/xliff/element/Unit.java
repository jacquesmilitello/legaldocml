package io.legaldocml.xliff.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;
import io.legaldocml.util.ToStringBuilder;
import io.legaldocml.xliff.attribute.CanResegment;
import io.legaldocml.xliff.attribute.Id;
import io.legaldocml.xliff.attribute.Name;
import io.legaldocml.xliff.type.YesNo;
import io.legaldocml.xliff.util.XliffList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;
import static io.legaldocml.xliff.element.XliffAttributes.ATTRIBUTE_CONSUMER;
import static io.legaldocml.xliff.element.XliffAttributes.ID;
import static io.legaldocml.xliff.element.XliffAttributes.NAME;
import static io.legaldocml.xliff.element.XliffElements.SEGMENT;
import static io.legaldocml.xliff.element.XliffElements.UNIT;

/**
 * <pre>
 *   <xs:element name="unit">
 *     <xs:complexType mixed="false">
 *       <xs:sequence>
 *         <xs:any minOccurs="0" maxOccurs="unbounded" namespace="##other" processContents="lax"/>
 *         <xs:element minOccurs="0" maxOccurs="1" ref="xlf:notes"/>
 *         <xs:element minOccurs="0" maxOccurs="1" ref="xlf:originalData"/>
 *         <xs:choice minOccurs="1" maxOccurs="unbounded">
 *           <xs:element ref="xlf:segment"/>
 *           <xs:element ref="xlf:ignorable"/>
 *         </xs:choice>
 *       </xs:sequence>
 *       <xs:attribute name="id" use="required" type="xs:NMTOKEN"/>
 *       <xs:attribute name="name" use="optional"/>
 *       <xs:attribute name="canResegment" use="optional" type="xlf:yesNo"/>
 *       <xs:attribute name="translate" use="optional" type="xlf:yesNo"/>
 *       <xs:attribute name="srcDir" use="optional" type="xlf:dirValue"/>
 *       <xs:attribute name="trgDir" use="optional" type="xlf:dirValue"/>
 *       <xs:attribute ref="xml:space" use="optional"/>
 *       <xs:attribute name="type" use="optional" type="xlf:userDefinedValue"/>
 *       <xs:anyAttribute namespace="##other" processContents="lax"/>
 *     </xs:complexType>
 *   </xs:element>
 * </pre>
 */
public final class Unit implements FileElement, Id, Name, CanResegment {

    /**
     * SLF4J logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Unit.class);

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(UNIT);


    private static final ImmutableMap<String, AttributeGetterSetter<XliffObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<XliffObject>>builder()
                .put(ID, attributeGetterSetter4String(ID, getFieldOffset(Unit.class, "id")))
                .put(NAME, attributeGetterSetter4String(NAME, getFieldOffset(Unit.class, "name")))
                .build();
    }

    private String id;
    private String name;
    private YesNo canResegment;

    private final XliffList<UnitElement> elements = new XliffList<>(new UnitElement[2]);

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
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCanResegment(YesNo canResegment) {
        this.canResegment = canResegment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 4);
        Id.super.write(writer);
        Name.super.write(writer);
        this.elements.write(writer);
        writer.writeEnd(ADDRESS,4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {

        reader.forEach(this, ATTRIBUTE_CONSUMER);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("unit ({})", this);
        }
        reader.nextStartOrEndElement();

        if (reader.getQName().equalsLocalName(SEGMENT)) {
            Segment segment;
            do {
                segment = new Segment();
                segment.read(reader);
                this.elements.add(segment);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(SEGMENT));
        }

        if (!reader.getQName().equalsLocalName(UNIT)) {
            // todo throw error
        } else {
            reader.next();
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
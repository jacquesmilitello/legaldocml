package io.legaldocml.xliff.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.ToStringBuilder;
import io.legaldocml.xliff.attribute.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;
import static io.legaldocml.xliff.element.XliffAttributes.ATTRIBUTE_CONSUMER;
import static io.legaldocml.xliff.element.XliffAttributes.ID;
import static io.legaldocml.xliff.element.XliffElements.UNIT;

/**
 * <pre>
 *  <xs:element name="file">
 *    <xs:complexType mixed="false">
 *      <xs:sequence>
 *        <xs:element minOccurs="0" maxOccurs="1" ref="xlf:skeleton"/>
 *        <xs:any minOccurs="0" maxOccurs="unbounded" namespace="##other" processContents="lax"/>
 *        <xs:element minOccurs="0" maxOccurs="1" ref="xlf:notes"/>
 *        <xs:choice minOccurs="1" maxOccurs="unbounded">
 *          <xs:element ref="xlf:unit"/>
 *          <xs:element ref="xlf:group"/>
 *        </xs:choice>
 *      </xs:sequence>
 *      <xs:attribute name="id" use="required" type="xs:NMTOKEN"/>
 *      <xs:attribute name="canResegment" use="optional" type="xlf:yesNo" default="yes"/>
 *      <xs:attribute name="original" use="optional"/>
 *      <xs:attribute name="translate" use="optional" type="xlf:yesNo" default="yes"/>
 *      <xs:attribute name="srcDir" use="optional" type="xlf:dirValue" default="auto"/>
 *      <xs:attribute name="trgDir" use="optional" type="xlf:dirValue" default="auto"/>
 *      <xs:attribute ref="xml:space" use="optional"/>
 *      <xs:anyAttribute namespace="##other" processContents="lax"/>
 *    </xs:complexType>
 *  </xs:element>
 * </pre>
 */
public final class File implements Id {

    /**
     * SLF4J logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(File.class);

    protected static final ImmutableMap<String, AttributeGetterSetter<XliffObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<XliffObject>>builder()
                .put(ID, attributeGetterSetter4String(ID, getFieldOffset(File.class, "id")))
                .build();
    }

    private String id;

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
    public void write(XmlWriter writer) throws IOException {
        Id.super.write(writer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {

        reader.forEach(this, ATTRIBUTE_CONSUMER);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("file ({})", this);

            reader.nextStartOrEndElement();

            if (reader.getQName().equalsLocalName(UNIT)) {
                Unit unit = new Unit();
                do {
                    unit = new Unit();
                    unit.read(reader);
                   // this.files.add(unit);
                    reader.nextStartOrEndElement();
                } while (reader.getQName().equalsLocalName(UNIT));
            }
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

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<XliffObject>> attributes() {
        return ATTRIBUTES;
    }
}
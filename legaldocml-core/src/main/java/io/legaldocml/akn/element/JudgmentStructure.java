package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Contains;
import io.legaldocml.akn.attribute.Name;
import io.legaldocml.akn.type.VersionType;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.element.Attributes.ATTRIBUTE_CONSUMER;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4Enum;
import static io.legaldocml.akn.element.Attributes.attributeRequireGetterSetter4String;
import static io.legaldocml.akn.util.XmlWriterHelper.writeContains;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The type judgmentStructure specifies the overall content model of the document types that describe judgments.
 * <p>
 * <pre>
 *   <xsd:complexType name="judgmentStructure">
 * 	   <xsd:sequence>
 * 	     <xsd:element ref="meta"/>
 * 		 <xsd:element ref="coverPage" minOccurs="0" maxOccurs="1"/>
 * 		 <xsd:element ref="header"/>
 * 		 <xsd:element ref="judgmentBody"/>
 * 		 <xsd:element ref="conclusions" minOccurs="0" maxOccurs="1"/>
 * 		 <xsd:element ref="attachments" minOccurs="0" maxOccurs="1"/>
 * 	     <xsd:element ref="components" minOccurs="0" maxOccurs="1"/>
 * 	   <xsd:sequence>
 * 	   <xsd:attributeGroup ref="name"/>
 * 	   <xsd:attributeGroup ref="contains"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class JudgmentStructure extends AbstractStructure implements Name, Contains {


    protected static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .put(AknAttributes.CONTAINS, attributeGetterSetter4Enum(AknAttributes.CONTAINS, getFieldOffset(JudgmentStructure.class, "versionType"), VersionType.class))
                .put(AknAttributes.NAME, attributeRequireGetterSetter4String(AknAttributes.NAME, getFieldOffset(JudgmentStructure.class, "name")))
                .build();
    }

    // Mandatory
    private final Header header = new Header();

    // Mandatory
    private final JudgmentBody body = new JudgmentBody();
    private String name;
    private VersionType versionType;

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VersionType getContains() {
        return this.versionType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContains(VersionType versionType) {
        this.versionType = versionType;
    }

    public JudgmentBody getBody() {
        return this.body;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        Name.super.write(writer);
        writeContains(writer, this);
        writeMeta(writer);
        writeCoverPage(writer);
        this.header.write(writer);
        this.body.write(writer);
        writeConclusionsAttachments(writer);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        reader.forEach(this, ATTRIBUTE_CONSUMER);
        reader.nextStartOrEndElement();
        readMeta(reader);
        readCoverPage(reader);
        this.header.read(reader);
        reader.nextStartOrEndElement();
        this.body.read(reader);
        reader.nextStartOrEndElement();
        readConclusionsAttachments(reader);
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
        this.header.accept(visitor);
        this.body.accept(visitor);
    }

}
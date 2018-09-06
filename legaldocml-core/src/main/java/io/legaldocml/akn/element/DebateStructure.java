package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Contains;
import io.legaldocml.akn.attribute.Name;
import io.legaldocml.akn.type.VersionType;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.ToStringBuilder;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.CONTAINS;
import static io.legaldocml.akn.AknAttributes.NAME;
import static io.legaldocml.akn.element.Attributes.ATTRIBUTE_CONSUMER;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4Enum;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.akn.util.XmlWriterHelper.writeContains;
import static io.legaldocml.akn.util.XmlWriterHelper.writeName;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The type debateStructure specifies the overall content model of the document types that describe debates.
 * <p>
 * <pre>
 *   <xsd:complexType name="debateStructure">
 * 	   <xsd:sequence>
 * 	     <xsd:element ref="meta"/>
 * 		 <xsd:element ref="coverPage" minOccurs="0" maxOccurs="1"/>
 * 		 <xsd:element ref="preface" minOccurs="0" maxOccurs="1"/>
 * 		 <xsd:element ref="debateBody"/>
 * 		 <xsd:element ref="conclusions" minOccurs="0" maxOccurs="1"/>
 * 		 <xsd:element ref="attachments" minOccurs="0" maxOccurs="1"/>
 * 		 <xsd:element ref="components" minOccurs="0" maxOccurs="1"/>
 * 	   <xsd:sequence>
 *     <xsd:attributeGroup ref="name"/>
 * 	   <xsd:attribute name="contains" type="versionType" default="originalVersion"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class DebateStructure extends AbstractStructure implements Contains, Name {

    protected static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .put(CONTAINS, attributeGetterSetter4Enum(CONTAINS, getFieldOffset(DebateStructure.class, "versionType"), VersionType.class))
                .put(NAME, attributeGetterSetter4String(NAME, getFieldOffset(DebateStructure.class, "name")))
                .build();
    }

    private final DebateBody debateBody = new DebateBody();

    private VersionType versionType;

    private String name;

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

    public DebateBody getDebateBody() {
        return this.debateBody;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writeContains(writer, this);
        if (writer.getVersion() >= 3) {
            Name.super.write(writer);
        }
        writeMetaCoverPagePreface(writer);
        this.debateBody.write(writer);
        writeConclusionsAttachments(writer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        reader.forEach(this, ATTRIBUTE_CONSUMER);
        reader.nextStartOrEndElement();
        readMetaCoverPagePreface(reader);

        this.debateBody.read(reader);
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
    public final String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, false);
        builder.append(NAME, this.name);
        builder.append(CONTAINS, this.versionType);
        return builder.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        visitMeta(visitor);
        visitCoverPage(visitor);
        visitPreface(visitor);
        this.debateBody.accept(visitor);
        visitConclusions(visitor);
        visitAttachments(visitor);
        visitComponents(visitor);
    }

}
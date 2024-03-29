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

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.CONTAINS;
import static io.legaldocml.akn.AknAttributes.NAME;
import static io.legaldocml.akn.element.Attributes.ATTRIBUTE_CONSUMER;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4Enum;
import static io.legaldocml.akn.element.Attributes.attributeRequireGetterSetter4String;
import static io.legaldocml.akn.util.XmlWriterHelper.writeContains;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The type hierarchicalStructure specifies the overall content model of the document types that are hierarchical in
 * nature, especially acts and bills.
 *
 * <pre>
 * 	<xsd:complexType name="hierarchicalStructure">
 * 		<xsd:sequence>
 * 			<xsd:element ref="meta"/>
 * 			<xsd:element ref="coverPage" minOccurs="0" maxOccurs="1"/>
 * 			<xsd:element ref="preface" minOccurs="0" maxOccurs="1"/>
 * 			<xsd:element ref="preamble" minOccurs="0" maxOccurs="1"/>
 * 			<xsd:element ref="body"/>
 * 			<xsd:element ref="conclusions" minOccurs="0" maxOccurs="1"/>
 * 			<xsd:element ref="attachments" minOccurs="0" maxOccurs="1"/>
 * 		    <xsd:element ref="components" minOccurs="0" maxOccurs="1"/>
 * 		<xsd:sequence>
 * 		<xsd:attributeGroup ref="contains"/>
 *   	<xsd:attributeGroup ref="name"/>
 * 	  <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class HierarchicalStructure extends AbstractStructureWithPreamble
        implements Contains, Name {

    protected static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .put(CONTAINS, attributeGetterSetter4Enum(CONTAINS, getFieldOffset(HierarchicalStructure.class, "versionType"), VersionType.class))
                .put(NAME, attributeRequireGetterSetter4String(NAME, getFieldOffset(HierarchicalStructure.class, "name")))
                .build();
    }


    // Mandatory
    private final Body body = new Body();

    private VersionType versionType;

    private String name;

    /**
     * {@inheritDoc}
     */
    @Override
    public final VersionType getContains() {
        return this.versionType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setContains(VersionType type) {
        this.versionType = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setName(String name) {
        this.name = name;
    }

    public final Body getBody() {
        return this.body;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writeContains(writer, this);
        if (writer.getVersion() > 2) {
            Name.super.write(writer);
        }
        writeMetaCoverPagePreface(writer);
        writePreamble(writer);
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
        //TODO attributes
        readMetaCoverPagePreface(reader);
        readPreamble(reader);
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
        visitMeta(visitor);
        visitCoverPage(visitor);
        visitPreface(visitor);
        visitPreamble(visitor);
        this.body.accept(visitor);
        visitConclusions(visitor);
        visitAttachments(visitor);
        visitComponents(visitor);
    }

}
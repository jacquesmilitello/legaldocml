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
import static io.legaldocml.akn.element.Attributes.biConsumerEnum;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.util.XmlWriterHelper.writeContains;
import static io.legaldocml.akn.util.XmlWriterHelper.writeName;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The type openStructure specifies the overall content model of all the document types that do not have a specific and
 * peculiar structure.
 * <p>
 * <pre>
 *   <xsd:complexType name="openStructure">
 * 	   <xsd:sequence>
 * 	     <xsd:element ref="meta"/>
 * 		 <xsd:element ref="coverPage" minOccurs="0" maxOccurs="1"/>
 * 		 <xsd:element ref="preface" minOccurs="0" maxOccurs="1"/>
 * 		 <xsd:element ref="preamble" minOccurs="0" maxOccurs="1"/>
 * 		 <xsd:element ref="mainBody"/>
 * 		 <xsd:element ref="conclusions" minOccurs="0" maxOccurs="1"/>
 * 		 <xsd:element ref="attachments" minOccurs="0" maxOccurs="1"/>
 * 	     <xsd:element ref="components" minOccurs="0" maxOccurs="1"/>
 * 	   <xsd:sequence>
 * 	   <xsd:attributeGroup ref="name"/>
 * 	   <xsd:attribute name="contains" type="versionType" default="originalVersion"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class OpenStructure extends AbstractStructureWithPreamble implements Name, Contains {


    protected static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .put(CONTAINS, biConsumerEnum(CONTAINS, getFieldOffset(OpenStructure.class, "versionType"), VersionType.class))
                .put(NAME, biConsumerString(NAME, getFieldOffset(OpenStructure.class, "name")))
                .build();
    }

    // Mandatory
    private final MainBody mainBody = new MainBody();
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

    public MainBody getMainBody() {
        return this.mainBody;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writeName(writer, this);
        writeContains(writer, this);
        writeMetaCoverPagePreface(writer);
        writePreamble(writer);
        this.mainBody.write(writer);
        writeConclusionsAttachments(writer);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        Attributes.read(reader, this);
        reader.nextStartOrEndElement();
        readMetaCoverPagePreface(reader);
        readPreamble(reader);
        mainBody.read(reader);
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
        this.mainBody.accept(visitor);
        visitConclusions(visitor);
        visitAttachments(visitor);
        visitComponents(visitor);
    }

}
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
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.akn.util.XmlWriterHelper.writeContains;
import static io.legaldocml.akn.util.XmlWriterHelper.writeName;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The type collectionStructure specifies the overall content model of the document types that are collections of other
 * documents.
 * <p>
 * <pre>
 *   <xsd:complexType name="collectionStructure">
 * 	   <xsd:sequence>
 * 	     <xsd:element ref="meta"/>
 * 		 <xsd:element ref="coverPage" minOccurs="0" maxOccurs="1"/>
 * 		 <xsd:element ref="preface" minOccurs="0" maxOccurs="1"/>
 * 		 <xsd:element ref="preamble" minOccurs="0" maxOccurs="1"/>
 * 		 <xsd:element ref="collectionBody"/>
 * 		 <xsd:element ref="conclusions" minOccurs="0" maxOccurs="1"/>
 * 	  	 <xsd:element ref="attachments" minOccurs="0" maxOccurs="1"/>
 * 		 <xsd:element ref="components" minOccurs="0" maxOccurs="1"/>
 * 	   <xsd:sequence>
 * 	   <xsd:attributeGroup ref="name"/>
 *     <xsd:attributeGroup ref="contains"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class CollectionStructure extends AbstractStructureWithPreamble implements Contains, Name {

    protected static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .put(AknAttributes.CONTAINS, attributeGetterSetter4Enum(AknAttributes.CONTAINS, getFieldOffset(CollectionStructure.class, "versionType"), VersionType.class))
                .put(AknAttributes.NAME, attributeGetterSetter4String(AknAttributes.NAME, getFieldOffset(CollectionStructure.class, "name")))
                .build();
    }

    // Mandatory
    private final CollectionBody collectionBody = new CollectionBody();

    private VersionType versionType;

    private String name;

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
    public final void setContains(VersionType versionType) {
        this.versionType = versionType;
    }


    public final CollectionBody getBody() {
        return collectionBody;
    }

    /**
     * {@inheritDoc}
     */
    public void write(XmlWriter writer) throws IOException {
        writeContains(writer, this);
        if (writer.getVersion() == 3) {
            writeName(writer, this);
        } else {
            if (this.name != null) {
                writeName(writer, this);
            }
        }
        writeMetaCoverPagePreface(writer);
        writePreamble(writer);
        this.collectionBody.write(writer);
        writeConclusionsAttachments(writer);
    }

    /**
     * {@inheritDoc}
     */
    public void read(XmlReader reader) {
        reader.forEach(this, ATTRIBUTE_CONSUMER);
        reader.nextStartOrEndElement();
        readMetaCoverPagePreface(reader);
        readPreamble(reader);
        this.collectionBody.read(reader);
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
        this.collectionBody.accept(visitor);
        visitConclusions(visitor);
        visitAttachments(visitor);
        visitComponents(visitor);


    }
}
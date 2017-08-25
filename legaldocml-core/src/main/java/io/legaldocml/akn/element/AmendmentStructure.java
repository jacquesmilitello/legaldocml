package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Contains;
import io.legaldocml.akn.attribute.Name;
import io.legaldocml.akn.type.VersionType;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerEnum;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.util.XmlWriterHelper.writeContains;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * the type amendmentStructure specifies the overall content model of the document types that describe amendments.
 * <pre>
 *   <xsd:complexType name="amendmentStructure">
 * 	   <xsd:sequence>
 *       <xsd:element ref="meta" />
 * 	     <xsd:element ref="coverPage" minOccurs="0" maxOccurs="1" />
 * 		 <xsd:element ref="preface" minOccurs="0" maxOccurs="1" />
 * 		 <xsd:element ref="amendmentBody" />
 * 		 <xsd:element ref="conclusions" minOccurs="0" maxOccurs="1" />
 * 		 <xsd:element ref="attachments" minOccurs="0" maxOccurs="1" />
 * 		 <xsd:element ref="components" minOccurs="0" maxOccurs="1"/>
 * 	   <xsd:sequence>
 * 	   <xsd:attribute name="contains" type="versionType" default="originalVersion" />
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class AmendmentStructure extends AbstractStructure implements Contains, Name {

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .put(AknAttributes.CONTAINS, biConsumerEnum(getFieldOffset(AmendmentStructure.class, "contains"), VersionType.class))
                .put(Name.ATTRIBUTE, biConsumerString(getFieldOffset(AmendmentStructure.class, "name")))
                .build();
    }

    // Mandatory
    private final AmendmentBody amendmentBody = new AmendmentBody();

    private VersionType contains;

    private String name;

    public final AmendmentBody getAmendmentBody() {
        return this.amendmentBody;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public final VersionType getContains() {
        return this.contains;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setContains(VersionType versionType) {
        this.contains = versionType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writeContains(writer, this);
        writeMetaCoverPagePreface(writer);
        amendmentBody.write(writer);
        writeConclusionsAttachments(writer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        Attributes.read(reader, this);
        reader.nextStartOrEndElement();
        //TODO attributes
        readMetaCoverPagePreface(reader);
        this.amendmentBody.read(reader);
        reader.nextStartOrEndElement();
        readConclusionsAttachments(reader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }

}
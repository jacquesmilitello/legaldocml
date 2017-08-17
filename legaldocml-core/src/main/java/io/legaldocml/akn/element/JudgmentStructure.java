package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
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
import static io.legaldocml.akn.util.XmlWriterHelper.writeName;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The type judgmentStructure specifies the overall content model of the document types that describe judgments.
 *
 * <pre>
 *   &lt;xsd:complexType name="judgmentStructure"&gt;
 * 	   &lt;xsd:sequence&gt;
 * 	     &lt;xsd:element ref="meta"/&gt;
 * 		 &lt;xsd:element ref="coverPage" minOccurs="0" maxOccurs="1"/&gt;
 * 		 &lt;xsd:element ref="header"/&gt;
 * 		 &lt;xsd:element ref="judgmentBody"/&gt;
 * 		 &lt;xsd:element ref="conclusions" minOccurs="0" maxOccurs="1"/&gt;
 * 		 &lt;xsd:element ref="attachments" minOccurs="0" maxOccurs="1"/&gt;
 * 	     &lt;xsd:element ref="components" minOccurs="0" maxOccurs="1"/&gt;
 * 	   &lt;xsd:sequence&gt;
 * 	   &lt;xsd:attributeGroup ref="name"/&gt;
 * 	   &lt;xsd:attributeGroup ref="contains"/&gt;
 *   &lt;xsd:complexType&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class JudgmentStructure extends AbstractStructure implements Name, Contains {


    protected static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .put(Contains.ATTRIBUTE, biConsumerEnum(getFieldOffset(JudgmentStructure.class, "versionType"), VersionType.class))
                .put(Name.ATTRIBUTE, biConsumerString(getFieldOffset(JudgmentStructure.class, "name")))
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
        writeName(writer, this);
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
        Attributes.read(reader, this);
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
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }

}
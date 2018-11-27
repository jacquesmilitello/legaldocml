package io.legaldocml.xliff.element;

import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.model.Language;
import io.legaldocml.util.Buffers;
import io.legaldocml.xliff.attribute.Core;
import io.legaldocml.xliff.attribute.SrcLang;
import io.legaldocml.xliff.attribute.TrgLang;
import io.legaldocml.xliff.attribute.Version;
import io.legaldocml.xliff.util.XliffList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static io.legaldocml.xliff.element.XliffElements.FILE;
import static io.legaldocml.xliff.element.XliffElements.XLIFF;

/**
 * <pre>
 *   <xs:element name="xliff">
 *     <xs:complexType mixed="false">
 *       <xs:sequence>
 *         <xs:element minOccurs="1" maxOccurs="unbounded" ref="xlf:file"/>
 *       </xs:sequence>
 *       <xs:attribute name="version" use="required"/>
 *       <xs:attribute name="srcLang" use="required" type="xs:language"/>
 *       <xs:attribute name="trgLang" use="optional" type="xs:language"/>
 *       <xs:attribute ref="xml:space" use="optional" default="default"/>
 *       <xs:anyAttribute namespace="##other" processContents="lax"/>
 *     </xs:complexType>
 *   </xs:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Xliff implements Core, SrcLang, TrgLang, Version {

    /**
     * SLF4J logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Xliff.class);

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(XLIFF);

    private final XliffList<File> files = new XliffList<>(new File[4]);

    private Language srcLang;
    private Language trgLang;
    private String version;

    /**
     * {@inheritDoc}
     */
    @Override
    public Language getSrcLang() {
        return this.srcLang;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSrcLang(Language srcLang) {
        this.srcLang = srcLang;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Language getTrgLang() {
        return this.trgLang;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTrgLang(Language trgLang) {
        this.trgLang = trgLang;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getVersion() {
        return this.version;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStartDocument(ADDRESS, 5);
        Version.super.write(writer);
        SrcLang.super.write(writer);
        TrgLang.super.write(writer);
        this.files.write(writer);
        writer.writeEndDocument(ADDRESS, 5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {

        QName qName = reader.getQName();
        //XliffAttributes.read(reader, this);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("[{}]",qName);
        }

        reader.nextStartOrEndElement();

        if (reader.getQName().equalsLocalName(FILE)) {
            File file = new File();
            do {
                file = new File();
                file.read(reader);
                this.files.add(file);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(FILE));
        }


    }

}

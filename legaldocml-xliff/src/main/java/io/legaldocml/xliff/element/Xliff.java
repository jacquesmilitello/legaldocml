package io.legaldocml.xliff.element;

import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * <pre>
 *   <xs:element name="xliff">
 *     <xs:complexType mixed="false">
 *       <xs:sequence>
 *         <xs:element minOccurs="1" maxOccurs="unbounded" ref="xlf:file"/>
 *       </xs:sequence>
 *       <xs:attribute name="version" use="required"/>
 *       <xs:attribute name="srcLang" use="required"/>
 *       <xs:attribute name="trgLang" use="optional"/>
 *       <xs:attribute ref="xml:space" use="optional" default="default"/>
 *       <xs:anyAttribute namespace="##other" processContents="lax"/>
 *     </xs:complexType>
 *   </xs:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Xliff implements XliffObject{

    /**
     * SLF4J logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Xliff.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {

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



    }

}

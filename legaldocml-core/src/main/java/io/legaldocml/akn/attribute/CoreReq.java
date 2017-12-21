package io.legaldocml.akn.attribute;


import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.util.XmlWriterHelper.writeAlt;
import static io.legaldocml.akn.util.XmlWriterHelper.writeEnactment;
import static io.legaldocml.akn.util.XmlWriterHelper.writeHTMLattrs;
import static io.legaldocml.akn.util.XmlWriterHelper.writeIdReq;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRefers;

/**
 * This is the list of the core attributes that all elements in the content part of the document must have. In elements
 * using this attribute definition the refersTo attribute is optional but the eId attribute is required.
 * <p>
 * ```xml
 * <xsd:attributeGroup name="corereq">
 * <xsd:attributeGroup ref="core"/>
 * <xsd:attributeGroup ref="HTMLattrs"/>
 * <xsd:attributeGroup ref="enactment"/>
 * <xsd:attributeGroup ref="idreq"/>
 * <xsd:attributeGroup ref="refers"/>
 * <xsd:attributeGroup ref="xmllang"/>
 * <xsd:attributeGroup ref="alt"/>
 * <xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface CoreReq extends Core, HTMLattrs, Enactment, IdReq, RefersOpt, Alt {

    /**
     * {@inheritDoc}
     */
    @Override
    default void write(XmlWriter writer) throws IOException {
        Core.super.write(writer);
        writeIdReq(writer, this);
        writeRefers(writer, this);
        writeHTMLattrs(writer, this);
        writeEnactment(writer, this);
        writeAlt(writer, this);
    }

}
package io.legaldocml.module.xml.attribute;

import io.legaldocml.io.Attribute;
import io.legaldocml.iso.Language;

/**
 * This attribute specifies the human language in which the content of the element is expressed. ValueReq are taken from the RFC 4646. xml:lang is a reserved attribute of XML, and cannot be used for any other purpose than this one.
 * <p/>
 * <pre>
 * <xsd:attributeGroup name="xmllang">
 * 		<xsd:attribute ref="xml:lang"/>
 * </xsd:attributeGroup>
 * </pre>
 */
public interface XmlLang extends Attribute {

    /**
     * Attribute name for "xml:lang".
     */
    String ATTRIBUTE = "lang";

    Language getXmlLang();

    void setXmlLang(Language xmlLang);

}
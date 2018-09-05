package io.legaldocml.module.xml.attribute;

import io.legaldocml.io.CoreAttribute;

/**
 * This attribute denotes an attribute whose value should be interpreted as if declared to be of type ID.
 * This name is reserved by virtue of its definition in the xml:id specification.
 * <p/>
 * <pre>
 *   <xs:attribute name="id" type="xs:ID">
 * </pre>
 */
public interface XmlId extends CoreAttribute {

    /**
     * CoreAttribute name for "xml:id".
     */
    String ATTRIBUTE = "id";

    String ATTRIBUTE_WITH_NAMESPACE = "xml:id";

    String getXmlId();

    void setXmlId(String id);

}
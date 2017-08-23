package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * This attribute specifies the human language in which the document the element refers to is expressed. Values are
 * taken from the RFC 4646.
 *
 *
 * ```xml
 * <xsd:attributeGroup name="language">
 *   <xsd:attribute name="language" type="language" use="required"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Language extends AknObject {

    /**
     * Attribute name "language".
     */
    String ATTRIBUTE = "language";

    String getLanguage();

    void setLanguage(String language);

}
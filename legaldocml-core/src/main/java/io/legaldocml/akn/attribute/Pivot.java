package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * This attribute specifies the human language used as a pivot in the translation. Values are taken from the RFC 4646.
 *
 * ```xml
 * <xsd:attributeGroup name="pivot">
 *   <xsd:attribute name="pivot" type="language"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Pivot extends AknObject {

    String getPivot();

    void setPivot(String language);

}
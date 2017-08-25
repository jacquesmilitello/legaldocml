package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * The attribute originating is used to identify whether the event is the one originating this expression.
 *
 * ```xml
 * <xsd:attributeGroup name="originating">
 *   <xsd:attribute name="originatingExpression" type="xsd:boolean"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Originating extends AknObject {

    Boolean getOriginatingExpression();

    void setOriginatingExpression(Boolean originatingExpression);

}
package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * The attribute originating is used to identify whether the event is the one originating this expression.
 *
 * <pre>
 *   &lt;xsd:attributeGroup name="originating"&gt;
 * 	   &lt;xsd:attribute name="originatingExpression" type="xsd:boolean"/&gt;
 *   &lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Originating extends AknObject {

    /**
     * Attribute name "originatingExpression".
     */
    String ATTRIBUTE = "originatingExpression";

    Boolean getOriginatingExpression();

    void setOriginatingExpression(Boolean originatingExpression);
}
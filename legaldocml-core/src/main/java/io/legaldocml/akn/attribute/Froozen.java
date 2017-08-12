package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * The attribute frozen for the condition element specifies whether the condition is frozen or not in the time period of
 * this expression.
 *
 * <pre>
 * 	&lt;xsd:attributeGroup name="frozen"&gt;
 * 		&lt;xsd:attribute name="frozen" type="xsd:boolean"/&gt;
 * 	&lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Froozen extends AknObject {

    /**
     * Attribute name.
     */
    String ATTRIBUTE = "frozen";

    Boolean getFrozen();

    void setFrozen(Boolean frozen);
}
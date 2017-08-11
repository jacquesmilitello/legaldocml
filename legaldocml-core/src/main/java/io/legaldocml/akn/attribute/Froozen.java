package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * The attribute frozen for the condition element specifies whether the condition is frozen or not in the time period of
 * this expression.
 * <p/>
 * <pre>
 * 	<xsd:attributeGroup name="frozen">
 * 		<xsd:attribute name="frozen" type="xsd:boolean"/>
 * 	</xsd:attributeGroup>
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
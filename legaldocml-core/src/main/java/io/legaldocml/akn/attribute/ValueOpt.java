package io.legaldocml.akn.attribute;


/**
 * The attribute text is used to specify a text for metadata elements. In elements using this attribute definition the
 * text attribute is optional.
 *
 * ```xml
 * <xsd:attributeGroup name="optvalue">
 *   <xsd:attribute name="text" type="xsd:string"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ValueOpt extends Value {

}
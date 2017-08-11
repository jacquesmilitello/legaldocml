package io.legaldocml.akn.attribute;


/**
 * The attribute value is used to specify a value for metadata elements. In elements using this attribute definition the
 * value attribute is required.
 * <p/>
 * <pre>
 *   <xsd:attributeGroup name="value">
 *     <xsd:attribute name="value" type="xsd:string" use="required"/>
 *   </xsd:attributeGroup>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ValueReq extends Value {

}
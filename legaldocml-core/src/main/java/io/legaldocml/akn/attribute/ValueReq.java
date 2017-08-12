package io.legaldocml.akn.attribute;


/**
 * The attribute value is used to specify a value for metadata elements. In elements using this attribute definition the
 * value attribute is required.
 *
 * <pre>
 *   &lt;xsd:attributeGroup name="value"&gt;
 *     &lt;xsd:attribute name="value" type="xsd:string" use="required"/&gt;
 *   &lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ValueReq extends Value {

}
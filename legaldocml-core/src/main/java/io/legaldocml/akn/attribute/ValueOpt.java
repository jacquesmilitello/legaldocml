package io.legaldocml.akn.attribute;


/**
 * The attribute text is used to specify a text for metadata elements. In elements using this attribute definition the
 * text attribute is optional.
 *
 * <pre>
 *   &lt;xsd:attributeGroup name="optvalue"&gt;
 *     &lt;xsd:attribute name="text" type="xsd:string"/&gt;
 *   &lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ValueOpt extends Value {

}
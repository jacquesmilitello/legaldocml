package io.legaldocml.akn.attribute;

/**
 * This is the list of the core attributes that all elements in the content part of the document must have. In elements
 * using this attribute definition both the refersTo attribute and the id attribute are optional.
 *
 * <pre>
 * 	&lt;xsd:attributeGroup name="coreopt"&gt;
 * 		&lt;xsd:attributeGroup ref="core"/&gt;
 * 	 	&lt;xsd:attributeGroup ref="HTMLattrs"/&gt;
 * 		&lt;xsd:attributeGroup ref="enactment"/&gt;
 * 		&lt;xsd:attributeGroup ref="idopt"/&gt;
 * 		&lt;xsd:attributeGroup ref="refers"/&gt;
 * 		&lt;xsd:attributeGroup ref="xmllang"/&gt;
 * 		&lt;xsd:attributeGroup ref="alt"/&gt;
 * 	&lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface CoreOpt extends Core, HTMLattrs, Enactment, IdOpt, RefersOpt, Alt {

}
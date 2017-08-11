package io.legaldocml.akn.attribute;

/**
 * This is the list of the core attributes that all elements in the content part of the document must have. In elements
 * using this attribute definition both the refersTo attribute and the id attribute are optional.
 * <p>
 * <pre>
 * 	<xsd:attributeGroup name="coreopt">
 * 		<xsd:attributeGroup ref="core"/>
 * 	 	<xsd:attributeGroup ref="HTMLattrs"/>
 * 		<xsd:attributeGroup ref="enactment"/>
 * 		<xsd:attributeGroup ref="idopt"/>
 * 		<xsd:attributeGroup ref="refers"/>
 * 		<xsd:attributeGroup ref="xmllang"/>
 * 		<xsd:attributeGroup ref="alt"/>
 * 	</xsd:attributeGroup>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface CoreOpt extends Core, HTMLattrs, Enactment, IdOpt, RefersOpt, Alt {

}
package io.legaldocml.akn.attribute;


/**
 * The attribute href is used to specify an internal or external destination for a reference, a citation, an access to
 * the ontology or a hypertext link. In elements using this attribute definition the href attribute is required.
 * <p>
 * <pre>
 * 	<xsd:attributeGroup name="linkopt">
 * 		<xsd:attribute name="href" type="xsd:anyURI"/>
 * 	</xsd:attributeGroup>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface LinkOpt extends Link {

}
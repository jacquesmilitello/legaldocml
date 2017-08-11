package io.legaldocml.akn.attribute;

/**
 * These attributes are used in metadata to propose visible representations of the metadata itself. Both a full
 * visualization (attribute showAs) and an abbreviated one (attribute shortForm) can be specified.
 * <p/>
 * <pre>
 * 	<xsd:attributeGroup name="show">
 * 		<xsd:attribute name="showAs" type="xsd:string" use="required"/>
 * 		<xsd:attribute name="shortForm" type="xsd:string"/>
 * 	</xsd:attributeGroup>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ShowReq extends Show {

}
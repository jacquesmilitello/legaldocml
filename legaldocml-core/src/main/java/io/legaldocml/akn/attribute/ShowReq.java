package io.legaldocml.akn.attribute;

/**
 * These attributes are used in metadata to propose visible representations of the metadata itself. Both a full
 * visualization (attribute showAs) and an abbreviated one (attribute shortForm) can be specified.
 *
 * <pre>
 * 	&lt;xsd:attributeGroup name="show"&gt;
 * 		&lt;xsd:attribute name="showAs" type="xsd:string" use="required"/&gt;
 * 		&lt;xsd:attribute name="shortForm" type="xsd:string"/&gt;
 * 	&lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ShowReq extends Show {

}
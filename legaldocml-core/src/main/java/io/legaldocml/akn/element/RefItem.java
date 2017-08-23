package io.legaldocml.akn.element;

import io.legaldocml.akn.AknObject;

/**
 * Markup Interface.<br/>
 *
 * The group refItems is a list of types of references used in the references section.
 *
 * <pre>
 * 	<xsd:group name="refItems">
 * 		<xsd:choice>
 * 			<xsd:group ref="docRefs"/>
 * 			<xsd:group ref="TLCs"/>
 * 		<xsd:choice>
 * 	<xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface RefItem extends AknObject {

}
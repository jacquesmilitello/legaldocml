package io.legaldocml.akn.element;

import io.legaldocml.akn.AknObject;

/**
 * Markup Interface.<br/&gt;
 *
 * The group refItems is a list of types of references used in the references section.
 *
 * <pre>
 * 	&lt;xsd:group name="refItems"&gt;
 * 		&lt;xsd:choice&gt;
 * 			&lt;xsd:group ref="docRefs"/&gt;
 * 			&lt;xsd:group ref="TLCs"/&gt;
 * 		&lt;xsd:choice&gt;
 * 	&lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface RefItem extends AknObject {

}
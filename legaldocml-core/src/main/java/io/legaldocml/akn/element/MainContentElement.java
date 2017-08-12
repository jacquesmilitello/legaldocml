package io.legaldocml.akn.element;

import io.legaldocml.akn.AknObject;

/**
 * Markup interface.
 *
 * <pre>
 * 	&lt;xsd:choice minOccurs="1" maxOccurs="unbounded"&gt;
 * 		&lt;xsd:element ref="componentRef"/&gt;
 * 		&lt;xsd:group ref="hierElements"/&gt;
 * 		&lt;xsd:group ref="blockElements"/&gt;
 * 		&lt;xsd:group ref="containerElements"/&gt;
 * 	&lt;xsd:choice&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface MainContentElement extends AknObject {

}
package io.legaldocml.akn.element;

import io.legaldocml.akn.AknObject;

/**
 * Markup interface.
 * <p/>
 * <pre>
 * 	<xsd:choice minOccurs="1" maxOccurs="unbounded">
 * 		<xsd:element ref="componentRef"/>
 * 		<xsd:group ref="hierElements"/>
 * 		<xsd:group ref="blockElements"/>
 * 		<xsd:group ref="containerElements"/>
 * 	</xsd:choice>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface MainContentElement extends AknObject {

}
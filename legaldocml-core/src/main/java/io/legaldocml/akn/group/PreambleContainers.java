package io.legaldocml.akn.group;

import io.legaldocml.akn.element.PreambleoptElement;

/**
 * The group preambleContainers lists the elements that are containers and are specific to the Akoma Ntoso preamble
 * vocabulary.
 *
 * <pre>
 *   <xsd:group name="preambleContainers">
 *     <xsd:choice>
 * 	     <xsd:element ref="recitals"/>
 * 		 <xsd:element ref="citations"/>
 * 	 	 <xsd:element ref="formula"/>
 * 		 <xsd:element ref="container"/>
 * 	   <xsd:choice>
 *   <xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface PreambleContainers extends PreambleoptElement {

}
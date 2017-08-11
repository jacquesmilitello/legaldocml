package io.legaldocml.akn.group;

import io.legaldocml.akn.element.BasicoptElement;

/**
 * The group basicContainers lists the elements that are containers and are specific to vocabulary of preambles,
 * prefaces, conclusions and coverPages.
 * <p/>
 * <pre>
 *   <xsd:group name="basicContainers">
 * 	   <xsd:choice>
 * 	     <xsd:element ref="longTitle"/>
 * 		 <xsd:element ref="formula"/>
 * 		 <xsd:element ref="container"/>
 * 	   </xsd:choice>
 *   </xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface BasicContainers extends BasicoptElement {

}
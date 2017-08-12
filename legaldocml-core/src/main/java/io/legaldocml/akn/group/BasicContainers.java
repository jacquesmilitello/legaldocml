package io.legaldocml.akn.group;

import io.legaldocml.akn.element.BasicoptElement;

/**
 * The group basicContainers lists the elements that are containers and are specific to vocabulary of preambles,
 * prefaces, conclusions and coverPages.
 *
 * <pre>
 *   &lt;xsd:group name="basicContainers"&gt;
 * 	   &lt;xsd:choice&gt;
 * 	     &lt;xsd:element ref="longTitle"/&gt;
 * 		 &lt;xsd:element ref="formula"/&gt;
 * 		 &lt;xsd:element ref="container"/&gt;
 * 	   &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface BasicContainers extends BasicoptElement {

}
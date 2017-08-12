package io.legaldocml.akn.group;

import io.legaldocml.akn.element.PreambleoptElement;

/**
 * The group preambleContainers lists the elements that are containers and are specific to the Akoma Ntoso preamble
 * vocabulary.
 *
 * <pre>
 *   &lt;xsd:group name="preambleContainers"&gt;
 *     &lt;xsd:choice&gt;
 * 	     &lt;xsd:element ref="recitals"/&gt;
 * 		 &lt;xsd:element ref="citations"/&gt;
 * 	 	 &lt;xsd:element ref="formula"/&gt;
 * 		 &lt;xsd:element ref="container"/&gt;
 * 	   &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface PreambleContainers extends PreambleoptElement {

}
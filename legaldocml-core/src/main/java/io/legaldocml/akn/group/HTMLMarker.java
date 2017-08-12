package io.legaldocml.akn.group;

/**
 * The group HTMLmarker lists the elements that are marker and were inherited from the HTML vocabulary.
 *
 * <pre>
 *   &lt;xsd:group name="HTMLmarker"&gt;
 * 	   &lt;xsd:sequence&gt;
 * 	     &lt;xsd:element ref="img" /&gt;
 * 		 &lt;xsd:element ref="br"/&gt;
 * 	   &lt;xsd:sequence&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface HTMLMarker extends MarkerElements {

}
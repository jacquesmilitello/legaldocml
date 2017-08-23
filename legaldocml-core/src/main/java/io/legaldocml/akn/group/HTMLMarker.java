package io.legaldocml.akn.group;

/**
 * The group HTMLmarker lists the elements that are marker and were inherited from the HTML vocabulary.
 *
 * <pre>
 *   <xsd:group name="HTMLmarker">
 * 	   <xsd:sequence>
 * 	     <xsd:element ref="img" />
 * 		 <xsd:element ref="br"/>
 * 	   <xsd:sequence>
 *   <xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface HTMLMarker extends MarkerElements {

}
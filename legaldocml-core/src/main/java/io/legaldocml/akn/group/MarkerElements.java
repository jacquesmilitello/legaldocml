package io.legaldocml.akn.group;

/**
 * The group markerElements lists all the elements that are markers.
 *
 * <pre>
 *   <xsd:group name="markerElements">
 * 	   <xsd:choice>
 * 	     <xsd:group ref="ANmarker"/>
 * 	     <xsd:group ref="HTMLmarker"/>
 * 	     <xsd:element ref="marker"/>
 *     <xsd:choice>
 *   <xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface MarkerElements extends InlineCM {

}
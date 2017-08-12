package io.legaldocml.akn.group;

/**
 * The group markerElements lists all the elements that are markers.
 *
 * <pre>
 *   &lt;xsd:group name="markerElements"&gt;
 * 	   &lt;xsd:choice&gt;
 * 	     &lt;xsd:group ref="ANmarker"/&gt;
 * 	     &lt;xsd:group ref="HTMLmarker"/&gt;
 * 	     &lt;xsd:element ref="marker"/&gt;
 *     &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface MarkerElements extends InlineCM {

}
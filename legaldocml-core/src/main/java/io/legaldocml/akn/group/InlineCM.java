package io.legaldocml.akn.group;

import io.legaldocml.akn.element.LiElement;
import io.legaldocml.akn.element.ModTypeItem;

/**
 * The group inlineCM is the content model of blocks and inlines, and is composed of all the inlines and all the
 * markers.
 * <p>
 * <pre>
 *   <xsd:group name="inlineCM">
 * 	   <xsd:choice>
 * 	     <xsd:group ref="inlineElements"/>
 * 		 <xsd:group ref="markerElements"/>
 * 		 <xsd:group ref="subFlowElements"/>
 *     </xsd:choice>
 *   </xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface InlineCM extends ModTypeItem, LiElement {

}
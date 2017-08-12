package io.legaldocml.akn.group;

import io.legaldocml.akn.element.LiElement;
import io.legaldocml.akn.element.ModTypeItem;

/**
 * The group inlineCM is the content model of blocks and inlines, and is composed of all the inlines and all the
 * markers.
 *
 * <pre>
 *   &lt;xsd:group name="inlineCM"&gt;
 * 	   &lt;xsd:choice&gt;
 * 	     &lt;xsd:group ref="inlineElements"/&gt;
 * 		 &lt;xsd:group ref="markerElements"/&gt;
 * 		 &lt;xsd:group ref="subFlowElements"/&gt;
 *     &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface InlineCM extends ModTypeItem, LiElement {

}
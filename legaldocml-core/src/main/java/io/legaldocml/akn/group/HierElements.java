package io.legaldocml.akn.group;

import io.legaldocml.akn.element.BodyTypeElement;
import io.legaldocml.akn.element.HierarchyElement;
import io.legaldocml.akn.element.MainContentElement;
import io.legaldocml.akn.element.PopupStructureElement;
import io.legaldocml.akn.element.PortionBodyTypeElement;
import io.legaldocml.akn.element.SubFlowStructureElement;

/**
 * The group hierElements lists all the elements that are hierarchical.
 *
 * <pre>
 *   &lt;xsd:group name="hierElements"&gt;
 * 	   &lt;xsd:choice&gt;
 * 	     &lt;xsd:group ref="ANhier"/&gt;
 * 		 &lt;xsd:element ref="hcontainer"/&gt;
 * 	   &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface HierElements extends PopupStructureElement, HierarchyElement, BodyTypeElement, MainContentElement, SubFlowStructureElement, PortionBodyTypeElement {

}
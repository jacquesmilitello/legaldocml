package io.legaldocml.akn.group;

import io.legaldocml.akn.element.BodyTypeElement;
import io.legaldocml.akn.element.HierarchyElement;
import io.legaldocml.akn.element.MainContentElement;
import io.legaldocml.akn.element.PopupStructureElement;
import io.legaldocml.akn.element.PortionBodyTypeElement;
import io.legaldocml.akn.element.SubFlowStructureElement;

/**
 * The group hierElements lists all the elements that are hierarchical.
 * <p/>
 * <pre>
 *   <xsd:group name="hierElements">
 * 	   <xsd:choice>
 * 	     <xsd:group ref="ANhier"/>
 * 		 <xsd:element ref="hcontainer"/>
 * 	   </xsd:choice>
 *   </xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface HierElements extends PopupStructureElement, HierarchyElement, BodyTypeElement, MainContentElement, SubFlowStructureElement, PortionBodyTypeElement {

}
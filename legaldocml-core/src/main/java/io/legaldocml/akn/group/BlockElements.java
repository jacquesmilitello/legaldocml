package io.legaldocml.akn.group;

import io.legaldocml.akn.element.AltHierarchyElement;
import io.legaldocml.akn.element.BasicoptElement;
import io.legaldocml.akn.element.BlockContainerTypeElement;
import io.legaldocml.akn.element.ContainerElement;
import io.legaldocml.akn.element.MainContentElement;
import io.legaldocml.akn.element.PopupStructureElement;
import io.legaldocml.akn.element.PreambleoptElement;
import io.legaldocml.akn.element.PrefaceoptElement;
import io.legaldocml.akn.element.SubFlowStructureElement;

/**
 * The group blockElements lists all the elements that are blocks.
 *
 * <pre>
 *   <xsd:group name="blockElements">
 * 	   <xsd:choice>
 * 	     <xsd:group ref="ANblock"/>
 * 		 <xsd:group ref="HTMLblock"/>
 * 		 <xsd:element ref="foreign"/>
 * 		 <xsd:element ref="block"/>
 * 	   <xsd:choice>
 *   <xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface BlockElements extends BasicoptElement, PrefaceoptElement, ContainerElement, PreambleoptElement, PopupStructureElement, MainContentElement, AltHierarchyElement, SubFlowStructureElement, BlockContainerTypeElement {

}
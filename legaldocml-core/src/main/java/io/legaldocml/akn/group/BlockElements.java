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
 *   &lt;xsd:group name="blockElements"&gt;
 * 	   &lt;xsd:choice&gt;
 * 	     &lt;xsd:group ref="ANblock"/&gt;
 * 		 &lt;xsd:group ref="HTMLblock"/&gt;
 * 		 &lt;xsd:element ref="foreign"/&gt;
 * 		 &lt;xsd:element ref="block"/&gt;
 * 	   &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface BlockElements extends BasicoptElement, PrefaceoptElement, ContainerElement, PreambleoptElement, PopupStructureElement, MainContentElement, AltHierarchyElement, SubFlowStructureElement, BlockContainerTypeElement {

}
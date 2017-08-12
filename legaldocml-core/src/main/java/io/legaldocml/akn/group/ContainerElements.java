package io.legaldocml.akn.group;

import io.legaldocml.akn.element.AltHierarchyElement;
import io.legaldocml.akn.element.MainContentElement;
import io.legaldocml.akn.element.PopupStructureElement;
import io.legaldocml.akn.element.PortionBodyTypeElement;
import io.legaldocml.akn.element.SubFlowStructureElement;

/**
 * The group containerElements lists all the elements that are containers.
 *
 * <pre>
 *   &lt;xsd:group name="containerElements"&gt;
 * 	   &lt;xsd:choice&gt;
 * 	     &lt;xsd:group ref="speechSection"/&gt;
 * 		 &lt;xsd:group ref="HTMLcontainers"/&gt;
 * 		 &lt;xsd:element ref="container"/&gt;
 * 	   &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ContainerElements extends PopupStructureElement, MainContentElement, AltHierarchyElement, SubFlowStructureElement, PortionBodyTypeElement {

}
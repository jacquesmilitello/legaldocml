package io.legaldocml.akn.group;

import io.legaldocml.akn.element.AltHierarchyElement;
import io.legaldocml.akn.element.MainContentElement;
import io.legaldocml.akn.element.PopupStructureElement;
import io.legaldocml.akn.element.PortionBodyTypeElement;
import io.legaldocml.akn.element.SubFlowStructureElement;

/**
 * The group containerElements lists all the elements that are containers.
 * <p/>
 * <pre>
 *   <xsd:group name="containerElements">
 * 	   <xsd:choice>
 * 	     <xsd:group ref="speechSection"/>
 * 		 <xsd:group ref="HTMLcontainers"/>
 * 		 <xsd:element ref="container"/>
 * 	   </xsd:choice>
 *   </xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ContainerElements extends PopupStructureElement, MainContentElement, AltHierarchyElement, SubFlowStructureElement, PortionBodyTypeElement {

}
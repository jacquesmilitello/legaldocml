package io.legaldocml.akn.group;


import io.legaldocml.akn.element.AltHierarchyElement;
import io.legaldocml.akn.element.PopupStructureElement;
import io.legaldocml.akn.element.PortionBodyTypeElement;
import io.legaldocml.akn.element.SubFlowStructureElement;

/**
 * The group ANcontainers lists the elements that are containers and are specific to the Akoma Ntoso debate vocabulary.
 *
 * <pre>
 *   <xsd:group name="ANcontainers">
 * 	   <xsd:choice>
 * 	     <xsd:element ref="speechGroup"/>
 * 		 <xsd:element ref="speech"/>
 * 		 <xsd:element ref="question"/>
 * 	     <xsd:element ref="answer"/>
 * 	     <xsd:element ref="other"/>
 * 		 <xsd:element ref="scene"/>
 * 		 <xsd:element ref="narrative"/>
 * 		 <xsd:element ref="summary"/>
 * 	   <xsd:choice>
 *   <xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANcontainers extends PopupStructureElement, AltHierarchyElement, SubFlowStructureElement, PortionBodyTypeElement {

}
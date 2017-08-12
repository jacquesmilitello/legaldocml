package io.legaldocml.akn.group;


import io.legaldocml.akn.element.AltHierarchyElement;
import io.legaldocml.akn.element.PopupStructureElement;
import io.legaldocml.akn.element.PortionBodyTypeElement;
import io.legaldocml.akn.element.SubFlowStructureElement;

/**
 * The group ANcontainers lists the elements that are containers and are specific to the Akoma Ntoso debate vocabulary.
 *
 * <pre>
 *   &lt;xsd:group name="ANcontainers"&gt;
 * 	   &lt;xsd:choice&gt;
 * 	     &lt;xsd:element ref="speechGroup"/&gt;
 * 		 &lt;xsd:element ref="speech"/&gt;
 * 		 &lt;xsd:element ref="question"/&gt;
 * 	     &lt;xsd:element ref="answer"/&gt;
 * 	     &lt;xsd:element ref="other"/&gt;
 * 		 &lt;xsd:element ref="scene"/&gt;
 * 		 &lt;xsd:element ref="narrative"/&gt;
 * 		 &lt;xsd:element ref="summary"/&gt;
 * 	   &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANcontainers extends PopupStructureElement, AltHierarchyElement, SubFlowStructureElement, PortionBodyTypeElement {

}
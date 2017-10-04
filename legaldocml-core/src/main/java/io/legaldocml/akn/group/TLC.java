package io.legaldocml.akn.group;


import io.legaldocml.akn.attribute.Id;
import io.legaldocml.akn.element.RefItem;

/**
 * The group TLCs is a list of types of Top Level classes of the Akoma Ntoso ontology.
 * 
 * <pre>
 *   <xsd:group name="TLCs">
 * 	   <xsd:choice>
 * 	     <xsd:element ref="TLCPerson"/>
 * 		 <xsd:element ref="TLCOrganization"/>
 * 		 <xsd:element ref="TLCConcept"/>
 * 		 <xsd:element ref="TLCObject"/>
 * 		 <xsd:element ref="TLCEvent"/>
 * 		 <xsd:element ref="TLCLocation"/>
 * 		 <xsd:element ref="TLCProcess"/>
 * 		 <xsd:element ref="TLCRole"/>
 * 		 <xsd:element ref="TLCTerm"/>
 * 		 <xsd:element ref="TLCReference"/>
 *	   <xsd:choice>
 *   <xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface TLC extends RefItem, Id {

}
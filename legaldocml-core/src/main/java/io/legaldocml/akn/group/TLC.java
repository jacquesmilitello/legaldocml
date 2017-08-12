package io.legaldocml.akn.group;


import io.legaldocml.akn.element.RefItem;

/**
 * The group TLCs is a list of types of Top Level classes of the Akoma Ntoso ontology.
 * 
 * <pre>
 *   &lt;xsd:group name="TLCs"&gt;
 * 	   &lt;xsd:choice&gt;
 * 	     &lt;xsd:element ref="TLCPerson"/&gt;
 * 		 &lt;xsd:element ref="TLCOrganization"/&gt;
 * 		 &lt;xsd:element ref="TLCConcept"/&gt;
 * 		 &lt;xsd:element ref="TLCObject"/&gt;
 * 		 &lt;xsd:element ref="TLCEvent"/&gt;
 * 		 &lt;xsd:element ref="TLCLocation"/&gt;
 * 		 &lt;xsd:element ref="TLCProcess"/&gt;
 * 		 &lt;xsd:element ref="TLCRole"/&gt;
 * 		 &lt;xsd:element ref="TLCTerm"/&gt;
 * 		 &lt;xsd:element ref="TLCReference"/&gt;
 *	   &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface TLC extends RefItem {

}
package io.legaldocml.akn.type;

/**
 * The simple type ForceMods lists all the types of modifications in force as values for the type attribute of the
 * forceMod element.
 *
 * <pre>
 *   <xsd:simpleType name="ScopeMods">
 *     <xsd:restriction base="xsd:string">
 * 	     <xsd:enumeration text="entryIntoForce"/>
 * 		 <xsd:enumeration text="endOfEnactment"/>
 * 	 	 <xsd:enumeration text="postponementOfEntryIntoForce"/>
 * 	 	 <xsd:enumeration text="prorogationOfForce"/>
 * 		 <xsd:enumeration text="reEnactment"/>
 * 		 <xsd:enumeration text="unconstitutionality"/>
 * 	   <xsd:restriction>
 *   <xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum ForceMods {

    entryIntoForce, endOfEnactment, postponementOfEntryIntoForce, prorogationOfForce, reEnactment, unconstitutionality;

}
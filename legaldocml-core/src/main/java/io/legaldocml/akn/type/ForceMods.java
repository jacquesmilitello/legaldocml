package io.legaldocml.akn.type;

/**
 * The simple type ForceMods lists all the types of modifications in force as values for the type attribute of the
 * forceMod element.
 *
 * <pre>
 *   &lt;xsd:simpleType name="ScopeMods"&gt;
 *     &lt;xsd:restriction base="xsd:string"&gt;
 * 	     &lt;xsd:enumeration text="entryIntoForce"/&gt;
 * 		 &lt;xsd:enumeration text="endOfEnactment"/&gt;
 * 	 	 &lt;xsd:enumeration text="postponementOfEntryIntoForce"/&gt;
 * 	 	 &lt;xsd:enumeration text="prorogationOfForce"/&gt;
 * 		 &lt;xsd:enumeration text="reEnactment"/&gt;
 * 		 &lt;xsd:enumeration text="unconstitutionality"/&gt;
 * 	   &lt;xsd:restriction>
 *   &lt;xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum ForceMods {

    entryIntoForce, endOfEnactment, postponementOfEntryIntoForce, prorogationOfForce, reEnactment, unconstitutionality;

}
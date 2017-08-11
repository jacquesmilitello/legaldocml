package io.legaldocml.akn.type;

/**
 * The simple type EfficacyMods lists all the types of modifications in efficacy as values for the type attribute of the
 * efficacyMod element.
 * <p>
 * <pre>
 *   <xsd:simpleType name="EfficacyMods">
 * 	   <xsd:restriction base="xsd:string">
 * 	     <xsd:enumeration text="entryIntoEfficacy"/>
 * 		 <xsd:enumeration text="endOfEfficacy"/>
 * 		 <xsd:enumeration text="inapplication"/>
 * 		 <xsd:enumeration text="retroactivity"/>
 * 		 <xsd:enumeration text="extraefficacy"/>
 * 		 <xsd:enumeration text="postponementOfEfficacy"/>
 * 		 <xsd:enumeration text="prorogationOfEfficacy"/>
 * 	   </xsd:restriction>
 *   </xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum EfficacyMods {

    entryIntoEfficacy, endOfEfficacy, inapplication, retroactivity, extraefficacy, postponementOfEfficacy, prorogationOfEfficacy

}
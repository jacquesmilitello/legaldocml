package io.legaldocml.akn.type;

/**
 * The simple type EfficacyMods lists all the types of modifications in efficacy as values for the type attribute of the
 * efficacyMod element.
 *
 * <pre>
 *   &lt;xsd:simpleType name="EfficacyMods"&gt;
 * 	   &lt;xsd:restriction base="xsd:string"&gt;
 * 	     &lt;xsd:enumeration text="entryIntoEfficacy"/&gt;
 * 		 &lt;xsd:enumeration text="endOfEfficacy"/&gt;
 * 		 &lt;xsd:enumeration text="inapplication"/&gt;
 * 		 &lt;xsd:enumeration text="retroactivity"/&gt;
 * 		 &lt;xsd:enumeration text="extraefficacy"/&gt;
 * 		 &lt;xsd:enumeration text="postponementOfEfficacy"/&gt;
 * 		 &lt;xsd:enumeration text="prorogationOfEfficacy"/&gt;
 * 	   &lt;xsd:restriction>
 *   &lt;xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum EfficacyMods {

    entryIntoEfficacy, endOfEfficacy, inapplication, retroactivity, extraefficacy, postponementOfEfficacy, prorogationOfEfficacy

}
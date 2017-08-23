package io.legaldocml.akn.type;

/**
 * The simple type MeaningMods lists all the types of modifications in meaning as values for the type attribute of the
 * meaningMod element.
 *
 * <pre>
 *   <xsd:simpleType name="MeaningMods">
 * 	   <xsd:restriction base="xsd:string">
 * 	     <xsd:enumeration text="variation"/>
 * 		 <xsd:enumeration text="termModification"/>
 * 		 <xsd:enumeration text="authenticInterpretation"/>
 * 	   <xsd:restriction>
 *   <xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum MeaningMods {

    variation, termModification, authenticInterpretation

}
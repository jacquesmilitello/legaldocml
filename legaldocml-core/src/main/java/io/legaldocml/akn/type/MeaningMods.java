package io.legaldocml.akn.type;

/**
 * The simple type MeaningMods lists all the types of modifications in meaning as values for the type attribute of the
 * meaningMod element.
 *
 * <pre>
 *   &lt;xsd:simpleType name="MeaningMods"&gt;
 * 	   &lt;xsd:restriction base="xsd:string"&gt;
 * 	     &lt;xsd:enumeration text="variation"/&gt;
 * 		 &lt;xsd:enumeration text="termModification"/&gt;
 * 		 &lt;xsd:enumeration text="authenticInterpretation"/&gt;
 * 	   &lt;xsd:restriction>
 *   &lt;xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum MeaningMods {

    variation, termModification, authenticInterpretation

}
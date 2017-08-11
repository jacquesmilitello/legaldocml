package io.legaldocml.akn.type;

/**
 * The simple type TextualMods lists all the types of textual modifications as values for the type attribute of the
 * textualMod element.
 * <p>
 * <pre>
 *   <xsd:simpleType name="TextualMods">
 * 	   <xsd:restriction base="xsd:string">
 * 	     <xsd:enumeration text="repeal" />
 * 		 <xsd:enumeration text="substitution" />
 * 		 <xsd:enumeration text="insertion" />
 * 		 <xsd:enumeration text="replacement" />
 * 		 <xsd:enumeration text="renumbering" />
 * 		 <xsd:enumeration value="split"/>
 *       <xsd:enumeration value="join"/>
 * 	   </xsd:restriction>
 *   </xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum TextualMods {

    repeal, substitution, insertion, replacement, renumbering, split, join

}
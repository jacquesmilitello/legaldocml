package io.legaldocml.akn.type;

/**
 * The simple type TextualMods lists all the types of textual modifications as values for the type attribute of the
 * textualMod element.
 *
 * <pre>
 *   &lt;xsd:simpleType name="TextualMods"&gt;
 * 	   &lt;xsd:restriction base="xsd:string"&gt;
 * 	     &lt;xsd:enumeration text="repeal" /&gt;
 * 		 &lt;xsd:enumeration text="substitution" /&gt;
 * 		 &lt;xsd:enumeration text="insertion" /&gt;
 * 		 &lt;xsd:enumeration text="replacement" /&gt;
 * 		 &lt;xsd:enumeration text="renumbering" /&gt;
 * 		 &lt;xsd:enumeration value="split"/&gt;
 *       &lt;xsd:enumeration value="join"/&gt;
 * 	   &lt;xsd:restriction>
 *   &lt;xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum TextualMods {

    repeal, substitution, insertion, replacement, renumbering, split, join

}
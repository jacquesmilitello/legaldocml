package io.legaldocml.akn.type;

/**
 * This is the list of possible positions of the text being analyzed by the element in the analyis section.
 *
 * <pre>
 * 	&lt;xsd:simpleType name="posType"&gt;
 * 		&lt;xsd:restriction base="xsd:string"&gt;
 * 			&lt;xsd:enumeration text="start"/&gt;
 * 			&lt;xsd:enumeration text="before"/&gt;
 * 			&lt;xsd:enumeration text="inside"/&gt;
 * 			&lt;xsd:enumeration text="after"/&gt;
 * 			&lt;xsd:enumeration text="end"/&gt;
 * 			&lt;xsd:enumeration text="unspecified"/&gt;
 * 		&lt;xsd:restriction>
 * 	&lt;xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum PosType {

    start, before, inside, after, end, unspecified;

}
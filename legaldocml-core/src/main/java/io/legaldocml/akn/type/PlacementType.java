package io.legaldocml.akn.type;

/**
 * This is the list of allowed values for the placement attribute of notes.
 *
 * <pre>
 * 	&lt;xsd:simpleType name="placementType"&gt;
 * 		&lt;xsd:restriction base="xsd:string"&gt;
 * 			&lt;xsd:enumeration text="side"/&gt;
 * 			&lt;xsd:enumeration text="left"/&gt;
 * 			&lt;xsd:enumeration text="right"/&gt;
 * 			&lt;xsd:enumeration text="bottom"/&gt;
 * 			&lt;xsd:enumeration text="inline"/&gt;
 * 		&lt;xsd:restriction>
 * 	&lt;xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum PlacementType {

    side, left, right, bottom, inline

}
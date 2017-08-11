package io.legaldocml.akn.type;

/**
 * This is the list of possible positions of the text being analyzed by the element in the analyis section.
 * <p>
 * <pre>
 * 	<xsd:simpleType name="posType">
 * 		<xsd:restriction base="xsd:string">
 * 			<xsd:enumeration text="start"/>
 * 			<xsd:enumeration text="before"/>
 * 			<xsd:enumeration text="inside"/>
 * 			<xsd:enumeration text="after"/>
 * 			<xsd:enumeration text="end"/>
 * 			<xsd:enumeration text="unspecified"/>
 * 		</xsd:restriction>
 * 	</xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum PosType {

    start, before, inside, after, end, unspecified;

}
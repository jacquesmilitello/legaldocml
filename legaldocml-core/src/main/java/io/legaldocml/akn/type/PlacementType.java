package io.legaldocml.akn.type;

/**
 * This is the list of allowed values for the placement attribute of notes.
 * <p/>
 * <pre>
 * 	<xsd:simpleType name="placementType">
 * 		<xsd:restriction base="xsd:string">
 * 			<xsd:enumeration text="side"/>
 * 			<xsd:enumeration text="left"/>
 * 			<xsd:enumeration text="right"/>
 * 			<xsd:enumeration text="bottom"/>
 * 			<xsd:enumeration text="inline"/>
 * 		</xsd:restriction>
 * 	</xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum PlacementType {

    side, left, right, bottom, inline

}
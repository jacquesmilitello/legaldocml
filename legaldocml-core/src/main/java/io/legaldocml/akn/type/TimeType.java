package io.legaldocml.akn.type;

/**
 * This is the list of allowed values for the type attribute of the recordedTime element for the specification of an
 * explicit mention of a time (e.g., in a debate)
 *
 * <pre>
 *   <xsd:simpleType name="timeType">
 * 	   <xsd:restriction base="xsd:string">
 * 	     <xsd:enumeration text="startEvent"/>
 * 		 <xsd:enumeration text="endEvent"/>
 * 	   <xsd:restriction>
 *   <xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum TimeType {

    startEvent, endEvent

}
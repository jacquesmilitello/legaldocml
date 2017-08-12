package io.legaldocml.akn.type;

/**
 * This is the list of allowed values for the type attribute of the recordedTime element for the specification of an
 * explicit mention of a time (e.g., in a debate)
 *
 * <pre>
 *   &lt;xsd:simpleType name="timeType"&gt;
 * 	   &lt;xsd:restriction base="xsd:string"&gt;
 * 	     &lt;xsd:enumeration text="startEvent"/&gt;
 * 		 &lt;xsd:enumeration text="endEvent"/&gt;
 * 	   &lt;xsd:restriction>
 *   &lt;xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum TimeType {

    startEvent, endEvent

}
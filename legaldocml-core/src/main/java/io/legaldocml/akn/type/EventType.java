package io.legaldocml.akn.type;

/**
 * This is the list of allowed values for the type attribute of the eventRef element.
 *
 * <pre>
 *   <xsd:simpleType name="eventType">
 * 	   <xsd:restriction base="xsd:string">
 * 	     <xsd:restriction base="xsd:string">
 * 		 <xsd:enumeration text="generation"/>
 * 	 	 <xsd:enumeration text="amendment"/>
 * 		 <xsd:enumeration text="repeal"/>
 * 	   <xsd:restriction>
 *   <xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum EventType {

    generation, amendment, repeal

}
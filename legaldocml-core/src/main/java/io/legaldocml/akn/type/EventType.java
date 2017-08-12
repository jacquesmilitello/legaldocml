package io.legaldocml.akn.type;

/**
 * This is the list of allowed values for the type attribute of the eventRef element.
 *
 * <pre>
 *   &lt;xsd:simpleType name="eventType"&gt;
 * 	   &lt;xsd:restriction base="xsd:string"&gt;
 * 	     &lt;xsd:restriction base="xsd:string"&gt;
 * 		 &lt;xsd:enumeration text="generation"/&gt;
 * 	 	 &lt;xsd:enumeration text="amendment"/&gt;
 * 		 &lt;xsd:enumeration text="repeal"/&gt;
 * 	   &lt;xsd:restriction>
 *   &lt;xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum EventType {

    generation, amendment, repeal

}
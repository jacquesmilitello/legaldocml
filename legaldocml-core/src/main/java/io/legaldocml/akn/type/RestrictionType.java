package io.legaldocml.akn.type;

/**
 * This is the list of allowed values for the restriction type attribute.
 *
 * <pre>
 *   &lt;xsd:simpleType name="timeType"&gt;
 * 	   &lt;xsd:restriction base="xsd:string"&gt;
 * 	     &lt;xsd:enumeration value="jurisdiction"/&gt;
 * 	   &lt;xsd:restriction>
 *   &lt;xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum RestrictionType {

    jurisdiction

}
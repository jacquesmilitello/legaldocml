package io.legaldocml.akn.type;

/**
 * This is the list of allowed values for the type attribute of the remark element for the specification of editorial
 * remarks (e.g., applauses, laughters, etc.) especially within debate records
 * <p>
 * <pre>
 *   <xsd:simpleType name="remarkType">
 * 	   <xsd:restriction base="xsd:string">
 * 	     <xsd:enumeration text="sceneDescription"/>
 * 		 <xsd:enumeration text="phenomenon"/>
 * 		 <xsd:enumeration text="caption"/>
 * 		 <xsd:enumeration text="translation"/>
 * 	   </xsd:restriction>
 *   </xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum RemarkType {

    sceneDescription, phenomenon, caption, translation

}
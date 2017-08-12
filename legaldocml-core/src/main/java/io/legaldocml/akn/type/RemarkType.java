package io.legaldocml.akn.type;

/**
 * This is the list of allowed values for the type attribute of the remark element for the specification of editorial
 * remarks (e.g., applauses, laughters, etc.) especially within debate records
 *
 * <pre>
 *   &lt;xsd:simpleType name="remarkType"&gt;
 * 	   &lt;xsd:restriction base="xsd:string"&gt;
 * 	     &lt;xsd:enumeration text="sceneDescription"/&gt;
 * 		 &lt;xsd:enumeration text="phenomenon"/&gt;
 * 		 &lt;xsd:enumeration text="caption"/&gt;
 * 		 &lt;xsd:enumeration text="translation"/&gt;
 * 	   &lt;xsd:restriction>
 *   &lt;xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum RemarkType {

    sceneDescription, phenomenon, caption, translation

}
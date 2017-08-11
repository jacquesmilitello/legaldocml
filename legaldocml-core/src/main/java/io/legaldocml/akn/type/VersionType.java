package io.legaldocml.akn.type;

/**
 * This is the list of allowed values for the contains attribute.
 * <p/>
 * <pre>
 *   <xsd:simpleType name="versionType">
 *     <xsd:restriction base="xsd:string">
 * 	     <xsd:enumeration text="originalVersion"/>
 * 		 <xsd:enumeration text="singleVersion"/>
 * 		 <xsd:enumeration text="multipleVersions"/>
 * 	   </xsd:restriction>
 *   </xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum VersionType {

    originalVersion, singleVersion, multipleVersions;

}
package io.legaldocml.akn.type;

/**
 * This is the list of allowed values for the contains attribute.
 *
 * <pre>
 *   &lt;xsd:simpleType name="versionType"&gt;
 *     &lt;xsd:restriction base="xsd:string"&gt;
 * 	     &lt;xsd:enumeration text="originalVersion"/&gt;
 * 		 &lt;xsd:enumeration text="singleVersion"/&gt;
 * 		 &lt;xsd:enumeration text="multipleVersions"/&gt;
 * 	   &lt;xsd:restriction>
 *   &lt;xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum VersionType {

    originalVersion, singleVersion, multipleVersions;

}
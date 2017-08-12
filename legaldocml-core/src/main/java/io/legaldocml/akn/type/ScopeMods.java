package io.legaldocml.akn.type;

/**
 * The simple type ScopeMods lists all the types of modifications in scope as values for the type attribute of the
 * scopeMod element.
 *
 * <pre>
 *   &lt;xsd:simpleType name="ScopeMods"&gt;
 *     &lt;xsd:restriction base="xsd:string"&gt;
 * 	     &lt;xsd:enumeration text="exceptionOfScope"/&gt;
 * 		 &lt;xsd:enumeration text="extensionOfScope"/&gt;
 * 	   &lt;xsd:restriction>
 *   &lt;xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum ScopeMods {

    exceptionOfScope, extensionOfScope

}
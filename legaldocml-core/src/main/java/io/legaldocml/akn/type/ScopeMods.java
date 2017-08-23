package io.legaldocml.akn.type;

/**
 * The simple type ScopeMods lists all the types of modifications in scope as values for the type attribute of the
 * scopeMod element.
 *
 * <pre>
 *   <xsd:simpleType name="ScopeMods">
 *     <xsd:restriction base="xsd:string">
 * 	     <xsd:enumeration text="exceptionOfScope"/>
 * 		 <xsd:enumeration text="extensionOfScope"/>
 * 	   <xsd:restriction>
 *   <xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum ScopeMods {

    exceptionOfScope, extensionOfScope

}
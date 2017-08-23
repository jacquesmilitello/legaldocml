package io.legaldocml.akn.type;

/**
 * The simple type LegalSystemMods lists all the types of modifications in the legal system as values for the type
 * attribute of the LegalSystemMod element.
 *
 * <pre>
 *   <xsd:simpleType name="LegalSystemMods">
 * 	   <xsd:restriction base="xsd:string">
 * 	     <xsd:enumeration value="staticReference"/>
 *       <xsd:enumeration value="implementation"/>
 *       <xsd:enumeration value="ratification"/>
 *       <xsd:enumeration value="application"/>
 *       <xsd:enumeration value="legislativeDelegation"/>
 *       <xsd:enumeration value="deregulation"/>
 *       <xsd:enumeration value="conversion"/>
 *       <xsd:enumeration value="expiration"/>
 *       <xsd:enumeration value="reiteration"/>
 *       <xsd:enumeration value="remaking"/>
 *       <xsd:enumeration value="republication"/>
 * 	   <xsd:restriction>
 *   <xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum LegalSystemMods {

    staticReference, implementation, ratification, application, legislativeDelegation, deregulation, conversion, expiration, reiteration, remaking, republication

}
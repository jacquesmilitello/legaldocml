package io.legaldocml.akn.type;

/**
 * The simple type LegalSystemMods lists all the types of modifications in the legal system as values for the type
 * attribute of the LegalSystemMod element.
 *
 * <pre>
 *   &lt;xsd:simpleType name="LegalSystemMods"&gt;
 * 	   &lt;xsd:restriction base="xsd:string"&gt;
 * 	     &lt;xsd:enumeration value="staticReference"/&gt;
 *       &lt;xsd:enumeration value="implementation"/&gt;
 *       &lt;xsd:enumeration value="ratification"/&gt;
 *       &lt;xsd:enumeration value="application"/&gt;
 *       &lt;xsd:enumeration value="legislativeDelegation"/&gt;
 *       &lt;xsd:enumeration value="deregulation"/&gt;
 *       &lt;xsd:enumeration value="conversion"/&gt;
 *       &lt;xsd:enumeration value="expiration"/&gt;
 *       &lt;xsd:enumeration value="reiteration"/&gt;
 *       &lt;xsd:enumeration value="remaking"/&gt;
 *       &lt;xsd:enumeration value="republication"/&gt;
 * 	   &lt;xsd:restriction>
 *   &lt;xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum LegalSystemMods {

    staticReference, implementation, ratification, application, legislativeDelegation, deregulation, conversion, expiration, reiteration, remaking, republication

}
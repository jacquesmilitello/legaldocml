package io.legaldocml.akn.type;

/**
 * This is the list of allowed values for the type attribute of the result element.
 *
 * <pre>
 *   <xsd:simpleType name="timeType">
 * 	   <xsd:restriction base="xsd:string">
 *       <xsd:enumeration value="deny"/>
 *       <xsd:enumeration value="dismiss"/>
 *       <xsd:enumeration value="uphold"/>
 *       <xsd:enumeration value="revert"/>
 *       <xsd:enumeration value="replaceOrder"/>
 *       <xsd:enumeration value="remit"/>
 *       <xsd:enumeration value="decide"/>
 *       <xsd:enumeration value="approve"/>
 *     <xsd:restriction>
 *   <xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum ResultType {

    deny, dismiss, uphold, revert, replaceOrder, remit, decide, approve

}
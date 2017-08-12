package io.legaldocml.akn.type;

/**
 * This is the list of allowed values for the type attribute of the result element.
 *
 * <pre>
 *   &lt;xsd:simpleType name="timeType"&gt;
 * 	   &lt;xsd:restriction base="xsd:string"&gt;
 *       &lt;xsd:enumeration value="deny"/&gt;
 *       &lt;xsd:enumeration value="dismiss"/&gt;
 *       &lt;xsd:enumeration value="uphold"/&gt;
 *       &lt;xsd:enumeration value="revert"/&gt;
 *       &lt;xsd:enumeration value="replaceOrder"/&gt;
 *       &lt;xsd:enumeration value="remit"/&gt;
 *       &lt;xsd:enumeration value="decide"/&gt;
 *       &lt;xsd:enumeration value="approve"/&gt;
 *     &lt;xsd:restriction>
 *   &lt;xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum ResultType {

    deny, dismiss, uphold, revert, replaceOrder, remit, decide, approve

}
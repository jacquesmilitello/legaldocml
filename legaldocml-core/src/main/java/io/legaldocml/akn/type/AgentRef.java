package io.legaldocml.akn.type;

import io.legaldocml.akn.AttributeValueException;
import io.legaldocml.unsafe.UnsafeString;
import io.legaldocml.util.CharArray;
import io.legaldocml.util.Strings;

/**
 * These values are references to existing agents (i.e., TLCPerson or TLCOrganization) only, i.e., given an existing eId
 * of an agent of the form XYZ, the value is necessarily #XYZ.
 * <p>
 * <pre>
 *   <xsd:simpleType name="agentRef">
 *     <xsd:restriction base="xsd:anyURI"/>
 *   <xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AgentRef extends AbstractRef {

    public static final AgentRef EMPTY = new AgentRef(new char[0]);

    AgentRef(char[] value) {
        super(value);
    }

    public AgentRef(CharArray charArray) {
        super(charArray.value());
    }

    public static AgentRef valueOf(String value) {
        if (!Strings.isEmpty(value)) {
            return new AgentRef(UnsafeString.getChars(requireNonNull(value)));
        }
        throw new AttributeValueException("NPE : valueOf(null)");
    }

}

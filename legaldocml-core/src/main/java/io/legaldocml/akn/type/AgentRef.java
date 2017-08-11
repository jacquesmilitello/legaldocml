package io.legaldocml.akn.type;

import io.legaldocml.util.AbstractUri;

/**
 * These values are references to existing agents (i.e., TLCPerson or TLCOrganization) only, i.e., given an existing eId
 * of an agent of the form XYZ, the value is necessarily #XYZ.
 * <p/>
 * <pre>
 *   <xsd:simpleType name="agentRef">
 *     <xsd:restriction base="xsd:anyURI"/>
 *   </xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class AgentRef extends AbstractUri {

    private static final char REF = '#';

    public AgentRef(char[] value) {
        super(value);
    }

}

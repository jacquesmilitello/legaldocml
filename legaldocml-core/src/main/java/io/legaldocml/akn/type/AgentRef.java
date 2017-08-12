package io.legaldocml.akn.type;

import io.legaldocml.util.AbstractUri;

/**
 * These values are references to existing agents (i.e., TLCPerson or TLCOrganization) only, i.e., given an existing eId
 * of an agent of the form XYZ, the value is necessarily #XYZ.
 *
 * <pre>
 *   &lt;xsd:simpleType name="agentRef"&gt;
 *     &lt;xsd:restriction base="xsd:anyURI"/&gt;
 *   &lt;xsd:simpleType>
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

package io.legaldocml.akn.type;

import io.legaldocml.util.AbstractUri;

/**
 * These values are references to existing votes (i.e., quorum or count) only, i.e., given an existing eId of a vote
 * element of the form XYZ, the value is necessarily #XYZ.
 *
 * <pre>
 *   <xsd:simpleType name="agentRef">
 *     <xsd:restriction base="xsd:anyURI"/>
 *   <xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class VoteRef extends AbstractUri {

    private static final char REF = '#';

    public VoteRef(char[] value) {
        super(value);
    }

}

package io.legaldocml.akn.type;

import io.legaldocml.util.AbstractUri;

/**
 * These values are references to existing temporal groups only, i.e., given an existing eId of a temporal group of the
 * form XYZ, the value is necessarily #XYZ
 *
 * <pre>
 *   <xsd:simpleType name="roleRef">
 *     <xsd:restriction base="xsd:anyURI"/>
 *   <xsd:simpleType>
 * </pre
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class TemporalGroupRef extends AbstractUri {

    public TemporalGroupRef(char[] value) {
        super(value);
    }

}

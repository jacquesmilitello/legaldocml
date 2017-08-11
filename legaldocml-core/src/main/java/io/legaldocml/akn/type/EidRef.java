package io.legaldocml.akn.type;

import io.legaldocml.util.AbstractUri;

/**
 * These values are references to existing eIds only, i.e., given an existing eId of the form XYZ, the value is
 * necessarily #XYZ.
 * <p/>
 * <pre>
 *   <xsd:simpleType name="eIdRef">
 *     <xsd:restriction base="xsd:anyURI"/>
 *   </xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class EidRef extends AbstractUri {

    private static final char REF = '#';

    public EidRef(char[] value) {
        super(value);
    }

}

package io.legaldocml.akn.type;

import io.legaldocml.util.AbstractUri;

/**
 * These values are references to existing wIds only, i.e., given an existing wId of the form XYZ, the value is
 * necessarily #XYZ.
 * <p/>
 * <pre>
 *   <xsd:simpleType name="wIdRef">
 *     <xsd:restriction base="xsd:anyURI"/>
 *   </xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class WidRef extends AbstractUri {

    private static final char REF = '#';

    public WidRef(char[] value) {
        super(value);
    }

}

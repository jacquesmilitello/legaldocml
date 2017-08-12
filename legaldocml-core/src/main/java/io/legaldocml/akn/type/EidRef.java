package io.legaldocml.akn.type;

import io.legaldocml.util.AbstractUri;

/**
 * These values are references to existing eIds only, i.e., given an existing eId of the form XYZ, the value is
 * necessarily #XYZ.
 *
 * <pre>
 *   &lt;xsd:simpleType name="eIdRef"&gt;
 *     &lt;xsd:restriction base="xsd:anyURI"/&gt;
 *   &lt;xsd:simpleType>
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

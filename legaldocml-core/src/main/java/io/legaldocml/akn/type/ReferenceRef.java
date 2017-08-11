package io.legaldocml.akn.type;

import io.legaldocml.util.AbstractUri;

/**
 * These values are references to existing References elements only, i.e., given an existing eId of an element of the
 * References section of the form XYZ, the value is necessarily #XYZ
 * <p/>
 * <pre>
 *   <xsd:simpleType name="referenceRef">
 *     <xsd:restriction base="xsd:anyURI"/>
 *   </xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class ReferenceRef extends AbstractUri {

    public ReferenceRef(char[] value) {
        super(value);
    }

}

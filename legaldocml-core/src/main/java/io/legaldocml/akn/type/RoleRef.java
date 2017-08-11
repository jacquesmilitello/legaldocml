package io.legaldocml.akn.type;

import io.legaldocml.util.AbstractUri;

/**
 * These values are references to existing roles (i.e., TLCRole) only, i.e., given an existing eId of a role of the form
 * XYZ, the value is necessarily #XYZ.
 * <p/>
 * <pre>
 *   <xsd:simpleType name="roleRef">
 *     <xsd:restriction base="xsd:anyURI"/>
 *   </xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class RoleRef extends AbstractUri {

    public RoleRef(char[] value) {
        super(value);
    }

}

package io.legaldocml.akn.type;

import io.legaldocml.util.AbstractUri;

/**
 * These values are references to existing roles (i.e., TLCRole) only, i.e., given an existing eId of a role of the form
 * XYZ, the value is necessarily #XYZ.
 *
 * <pre>
 *   &lt;xsd:simpleType name="roleRef"&gt;
 *     &lt;xsd:restriction base="xsd:anyURI"/&gt;
 *   &lt;xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class RoleRef extends AbstractUri {

    public RoleRef(char[] value) {
        super(value);
    }

}

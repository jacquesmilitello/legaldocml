package io.legaldocml.akn.type;

import io.legaldocml.unsafe.UnsafeString;

import java.util.function.Function;

/**
 * These values are references to existing roles (i.e., TLCRole) only, i.e., given an existing eId of a role of the form
 * XYZ, the value is necessarily #XYZ.
 * <p>
 * <pre>
 *   <xsd:simpleType name="roleRef">
 *     <xsd:restriction base="xsd:anyURI"/>
 *   <xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class RoleRef extends AbstractRef {

    static final Function<char[], RoleRef> INSTANTIATOR_ROLE_REF = RoleRef::new;

    private RoleRef(char[] value) {
        super(value);
    }

    public static RoleRef raw(char[] raw) {
        return new RoleRef(raw);
    }

    public static RoleRef valueOf(String value) {
        return valueOf(UnsafeString.getChars(value), INSTANTIATOR_ROLE_REF);
    }

}

package io.legaldocml.akn.type;

import io.legaldocml.unsafe.UnsafeString;
import io.legaldocml.util.CharArray;

import java.util.Objects;

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

    RoleRef(char[] value) {
        super(value);
    }

    public RoleRef(CharArray charArray) {
        super(charArray.value());
    }

    public static RoleRef valueOf(String value) {
        return new RoleRef(UnsafeString.getChars(Objects.requireNonNull(value)));
    }

}

package io.legaldocml.akn.type;

import io.legaldocml.unsafe.UnsafeString;
import io.legaldocml.util.CharArray;

import static java.util.Objects.requireNonNull;

/**
 * These values are references to existing References elements only, i.e., given an existing eId of an element of the
 * References section of the form XYZ, the value is necessarily #XYZ
 *
 * <pre>
 *   <xsd:simpleType name="referenceRef">
 *     <xsd:restriction base="xsd:anyURI"/>
 *   <xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ReferenceRef extends AbstractRef {

    public ReferenceRef(CharArray charArray) {
        super(charArray.value());
    }

    ReferenceRef(char[] value) {
        super(value);
    }

    public static ReferenceRef valueOf(String value) {
        return new ReferenceRef(UnsafeString.getChars(requireNonNull(value)));
    }

}

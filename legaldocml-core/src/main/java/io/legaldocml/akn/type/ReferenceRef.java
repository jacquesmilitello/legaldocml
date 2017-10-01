package io.legaldocml.akn.type;

import io.legaldocml.unsafe.UnsafeString;

import java.util.function.Function;

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

    private static final Function<char[], ReferenceRef> INSTANTIATOR_REFERENCE_REF = ReferenceRef::new;

    private ReferenceRef(char[] value) {
        super(value);
    }

    public static ReferenceRef raw(char[] value) {
        return new ReferenceRef(value);
    }

    public static ReferenceRef valueOf(char[] value) {
        return valueOf(value, INSTANTIATOR_REFERENCE_REF);
    }

    public static ReferenceRef valueOf(String value) {
        return valueOf(UnsafeString.getChars(value));
    }

}

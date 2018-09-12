package io.legaldocml.akn.type;

import io.legaldocml.unsafe.UnsafeString;
import io.legaldocml.util.CharArray;

/**
 * These values are references to existing eIds only, i.e., given an existing eId of the form XYZ, the value is
 * necessarily #XYZ.
 *
 * <pre>
 *   <xsd:simpleType name="eIdRef">
 *     <xsd:restriction base="xsd:anyURI"/>
 *   <xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class EidRef extends AbstractRef {

    private EidRef(char[] value) {
        super(value);
    }

    public EidRef(CharArray charArray) {
        super(charArray.value());
    }

    public static EidRef valueOf(String value) {
        return new EidRef(UnsafeString.getChars(requireNonNull(value)));
    }

}

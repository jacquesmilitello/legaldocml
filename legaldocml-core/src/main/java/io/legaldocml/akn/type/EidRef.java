package io.legaldocml.akn.type;

import java.util.function.Function;

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

    private static final Function<char[], EidRef> INSTANTIATOR_EID_REF = EidRef::new;

    public EidRef(char[] value) {
        super(value);
    }

    public static EidRef valueOf(char[] value) {
        return valueOf(value, INSTANTIATOR_EID_REF);
    }
}

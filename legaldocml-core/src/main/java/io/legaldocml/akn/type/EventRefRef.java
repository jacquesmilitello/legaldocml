package io.legaldocml.akn.type;

import io.legaldocml.util.AbstractUri;

/**
 * These values are references to existing event references (i.e., eventRef) only, i.e., given an existing eId of an
 * eventRef of the form XYZ, the value is necessarily #XYZ.
 * <p/>
 * <pre>
 *   <xsd:simpleType name="eventRefRef">
 *     <xsd:restriction base="xsd:anyURI"/>
 *   </xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class EventRefRef extends AbstractUri {

    private static final char REF = '#';

    public EventRefRef(char[] value) {
        super(value);
    }

}

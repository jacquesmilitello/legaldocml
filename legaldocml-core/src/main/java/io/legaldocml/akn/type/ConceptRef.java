package io.legaldocml.akn.type;

import io.legaldocml.util.AbstractUri;

/**
 * These values are references to existing concepts (i.e., TLCConcept) only, i.e., given an existing eId of a concept of
 * the form XYZ, the value is necessarily #XYZ.
 * <p/>
 * <pre>
 *   <xsd:simpleType name="conceptRef">
 *     <xsd:restriction base="xsd:anyURI"/>
 *   </xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class ConceptRef extends AbstractUri {

    private static final char REF = '#';

    public ConceptRef(char[] value) {
        super(value);
    }

}

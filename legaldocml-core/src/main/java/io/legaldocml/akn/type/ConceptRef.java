package io.legaldocml.akn.type;

import io.legaldocml.util.AbstractUri;

/**
 * These values are references to existing concepts (i.e., TLCConcept) only, i.e., given an existing eId of a concept of
 * the form XYZ, the value is necessarily #XYZ.
 *
 * <pre>
 *   &lt;xsd:simpleType name="conceptRef"&gt;
 *     &lt;xsd:restriction base="xsd:anyURI"/&gt;
 *   &lt;xsd:simpleType>
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

package io.legaldocml.akn.type;

import io.legaldocml.util.AbstractUri;
import io.legaldocml.unsafe.UnsafeString;

/**
 * These values are references of a manifestation-Level document only
 * <p/>
 * <pre>
 *   <xsd:simpleType name="manifestationURI">
 *     <xsd:restriction base="xsd:anyURI"/>
 *   </xsd:simpleType>
 * </pre>
 */
public final class ManifestationURI extends AbstractUri {

    public ManifestationURI(char[] value) {
        super(value);
    }

    public static ManifestationURI valueOf(String value) {
        return new ManifestationURI(UnsafeString.getChars(value));
    }

}

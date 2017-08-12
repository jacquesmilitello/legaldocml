package io.legaldocml.akn.type;

import io.legaldocml.util.AbstractUri;
import io.legaldocml.unsafe.UnsafeString;

/**
 * These values are references of a manifestation-Level document only
 *
 * <pre>
 *   &lt;xsd:simpleType name="manifestationURI"&gt;
 *     &lt;xsd:restriction base="xsd:anyURI"/&gt;
 *   &lt;xsd:simpleType>
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

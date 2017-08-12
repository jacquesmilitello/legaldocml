package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.ManifestationURI;

/**
 * These attributes are used in manifestation-level references to specify external manifestation-level resources to be
 * loaded in place. The src attribute holds the manifestation-level IRI of the resource, while the alt attribute holds
 * the text to be displayed in case the loading of the external resource is not possible for any reason.
 *
 * <pre>
 *   &lt;xsd:attributeGroup name="src"&gt;
 * 	   &lt;xsd:attribute name="src" type="manifestationURI" use="required"/&gt;
 * 	   &lt;xsd:attribute name="alt" type="xsd:string"/&gt;
 * 	 &lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Src extends AknObject {

    /**
     * Attribute name "src".
     */
    String ATTRIBUTE_SRC = "src";
    /**
     * Attribute name "alt".
     */
    String ATTRIBUTE_ALT = "alt";

    ManifestationURI getSrc();

    void setSrc(ManifestationURI src);

    String getAlt();

    void setAlt(String alt);

}
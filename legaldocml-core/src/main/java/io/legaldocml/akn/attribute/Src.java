package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.ManifestationURI;

/**
 * These attributes are used in manifestation-level references to specify external manifestation-level resources to be
 * loaded in place. The src attribute holds the manifestation-level IRI of the resource, while the alt attribute holds
 * the text to be displayed in case the loading of the external resource is not possible for any reason.
 *
 * ```xml
 * <xsd:attributeGroup name="src">
 *   <xsd:attribute name="src" type="manifestationURI" use="required"/>
 *   <xsd:attribute name="alt" type="xsd:string"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Src extends AknObject {

    ManifestationURI getSrc();

    void setSrc(ManifestationURI src);

    String getAlt();

    void setAlt(String alt);

}
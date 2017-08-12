package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * This attribute specifies whether the translation is authoritative or not.
 *
 * <pre>
 *   &lt;xsd:attributeGroup name="authoritative"&gt;
 * 	   &lt;xsd:attribute name="authoritative" type="xsd:boolean" /&gt;
 *   &lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Authoritative extends AknObject {

    /**
     * Attribute "authoritative".
     */
    String ATTRIBUTE = "authoritative";

    Boolean getAuthoritative();

    void setAuthoritative(Boolean authoritative);
}
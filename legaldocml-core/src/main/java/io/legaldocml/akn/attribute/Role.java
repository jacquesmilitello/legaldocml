package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.RoleRef;

/**
 * The attribute role is used to identify the role of an individual mentioned in the text. It is a reference to a
 * TLCRole element in the references section
 *
 * <pre>
 *   &lt;xsd:attributeGroup name="role"&gt;
 * 	   &lt;xsd:attribute name="as" type="roleRef"/&gt;
 *   &lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Role extends AknObject {

    /**
     * Attribute name "as".
     */
    String ATTRIBUTE = "as";

    RoleRef getAs();

    void setAs(RoleRef as);

}
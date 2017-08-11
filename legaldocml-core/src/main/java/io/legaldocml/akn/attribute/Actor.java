package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * The attribute actor is used to identify the actor of a step of a workflow of the document. It is a reference to a
 * TLCPerson or TLCOrganization element in the references section.
 * <p/>
 * <pre>
 * 	<xsd:attributeGroup name="actor">
 * 		<xsd:attribute name="actor" type="xsd:anyURI"/>
 * 	</xsd:attributeGroup>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Actor extends AknObject {

    /**
     * Attribute name.
     */
    String ATTRIBUTE = "actor";

    String getActor();

    void setActor(String actor);
}
package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * The attribute actor is used to identify the actor of a step of a workflow of the document. It is a reference to a
 * TLCPerson or TLCOrganization element in the references section.
 *
 * ```xml
 * <xsd:attributeGroup name="actor">
 *   <xsd:attribute name="actor" type="xsd:anyURI"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Actor extends AknObject {

    String getActor();

    void setActor(String actor);
}
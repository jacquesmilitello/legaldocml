package io.legaldocml.akn.attribute;


import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.EidRef;

/**
 * These attributes are used in range-based elements to specify the eId of the beginning and ending element of the
 * range.
 *
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Range extends AknObject {

    EidRef getFrom();

    void setFrom(EidRef from);

}
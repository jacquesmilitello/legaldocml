package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.ListReferenceRef;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Refers extends AknObject {

    ListReferenceRef getRefersTo();

    void setRefersTo(ListReferenceRef refersTo);

}
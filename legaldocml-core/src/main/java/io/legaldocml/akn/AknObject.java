package io.legaldocml.akn;

import io.legaldocml.akn.visitor.Visitable;
import io.legaldocml.io.Externalizable;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface AknObject extends Externalizable, Visitable {

    /**
     * Name of this object (the name of the xml element)
     */
    String name();

}

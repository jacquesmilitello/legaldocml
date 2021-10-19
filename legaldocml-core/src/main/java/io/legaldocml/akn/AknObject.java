package io.legaldocml.akn;

import io.legaldocml.akn.visitor.Visitable;
import io.legaldocml.io.Externalizable;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface AknObject extends Externalizable<AknObject>, Visitable {

    /**
     * Name of this object (the name of the xml element)
     */
    String name();

    default <T extends AknObject> T clone(AknCloneContext cloneContext) {
        throw new UnsupportedOperationException("clone() not implemented for <" + name() + ">");
    }

    <T extends AknObject> T getParent();

    <T extends AknObject> void setParent(T parent);

}

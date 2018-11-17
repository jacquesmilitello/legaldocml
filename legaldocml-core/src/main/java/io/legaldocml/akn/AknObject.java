package io.legaldocml.akn;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.visitor.Visitable;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.Externalizable;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface AknObject extends Externalizable, Visitable {

    /**
     * Name of this object (the name of the xml element)
     */
    String name();

    /**
     * To read attributes.
     */
    default ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ImmutableMap.of();
    }


    default <T extends AknObject> T clone(AknCloneContext cloneContext) {
        throw new UnsupportedOperationException("clone() not implemented for <" + name() + ">");
    }

}

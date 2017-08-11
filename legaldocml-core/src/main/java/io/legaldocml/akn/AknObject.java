package io.legaldocml.akn;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.visitor.Visitable;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.Externalizable;

import java.util.function.BiConsumer;

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
    default ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        throw new UnsupportedOperationException(getClass() + " must implements");
    }

}

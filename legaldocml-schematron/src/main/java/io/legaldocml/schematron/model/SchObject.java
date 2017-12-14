package io.legaldocml.schematron.model;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.Externalizable;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface SchObject extends Externalizable {

    default ImmutableMap<String, AttributeGetterSetter<SchObject>> attributes() {
        throw new UnsupportedOperationException(getClass() + " must implement attributes()");
    }

}

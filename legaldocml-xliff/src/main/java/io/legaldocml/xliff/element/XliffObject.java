package io.legaldocml.xliff.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.Externalizable;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface XliffObject extends Externalizable {

    /**
     * To read attributes.
     */
    default ImmutableMap<String, AttributeGetterSetter<XliffObject>> attributes() {
        return ImmutableMap.of();
    }

}

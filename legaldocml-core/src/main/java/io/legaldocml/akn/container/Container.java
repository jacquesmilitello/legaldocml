package io.legaldocml.akn.container;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Id;

import java.util.stream.Stream;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Container<T extends AknObject> extends Id {

    default Stream<T> stream() {
        return Stream.empty();
    }

    T remove(int index);

}

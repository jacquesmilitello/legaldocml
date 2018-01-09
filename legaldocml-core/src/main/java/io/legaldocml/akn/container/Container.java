package io.legaldocml.akn.container;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Id;
import io.legaldocml.util.Iterables;
import io.legaldocml.util.ListIterable;

import java.util.stream.Stream;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Container<T extends AknObject> extends Id {

    default Stream<T> stream() {
        return Stream.empty();
    }

    default ListIterable<T> iterable() {
        return Iterables.empty();
    }

    T remove(int index);

}

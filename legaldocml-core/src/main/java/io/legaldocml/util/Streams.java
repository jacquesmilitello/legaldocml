package io.legaldocml.util;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Streams {

    private Streams() {
    }

    public static <T> Stream<T> stream(List<T> list) {
        if (list == null) {
            return Stream.of();
        } else {
            return list.stream();
        }
    }
}

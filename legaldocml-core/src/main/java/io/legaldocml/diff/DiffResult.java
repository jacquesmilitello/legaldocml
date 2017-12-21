package io.legaldocml.diff;

import java.util.stream.Stream;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface DiffResult {

    int count();

    Stream<Diff> stream();

}

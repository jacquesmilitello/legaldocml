package io.legaldocml.diff.impl;

import io.legaldocml.diff.Diff;
import io.legaldocml.diff.DiffResult;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class DiffResultImpl implements DiffResult {

    private final List<Diff> diffs;

    public DiffResultImpl(List<Diff> diffs) {
        this.diffs = diffs;
    }

    @Override
    public int count() {
        return this.diffs.size();
    }

    @Override
    public Stream<Diff> stream() {
        return this.diffs.stream();
    }

}

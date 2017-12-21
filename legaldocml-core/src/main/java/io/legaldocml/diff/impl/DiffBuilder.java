package io.legaldocml.diff.impl;

import io.legaldocml.akn.AknObject;
import io.legaldocml.diff.DiffResult;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DiffBuilder<T extends AknObject> {

    private T left;
    private T right;

    public DiffBuilder<T> left(T left) {
        this.left = left;
        return this;
    }

    public DiffBuilder<T> right(T right) {
        this.right = right;
        return this;
    }

    public DiffResult build() {
        DiffContextImpl context = new DiffContextImpl();
        left.compare(right,context);
        return context.result();
    }
}

package io.legaldocml.diff.impl;

import io.legaldocml.akn.AknObject;
import io.legaldocml.diff.ElementDiff;

abstract class AbstractElementDiff implements ElementDiff {

    private final String path;
    private final AknObject left;
    private final AknObject right;

    AbstractElementDiff(String path, AknObject left, AknObject right) {
        this.path = path;
        this.left = left;
        this.right = right;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPath() {
        return this.path;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AknObject getLeft() {
        return this.left;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AknObject getRight() {
        return this.right;
    }
}

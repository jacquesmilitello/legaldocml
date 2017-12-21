package io.legaldocml.diff.impl;

import io.legaldocml.akn.AknObject;
import io.legaldocml.diff.AttributeDiff;
import io.legaldocml.diff.DiffType;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class AttributeValueDiff implements AttributeDiff {

    private final DiffType diffType;
    private final String path;

    private final String name;
    private final Object valueLeft;
    private final Object valueRight;
    private final AknObject left;
    private final AknObject right;

    public AttributeValueDiff(DiffType diffType, String path, String name, Object valueLeft, Object valueRight, AknObject left, AknObject right) {
        this.diffType = diffType;
        this.path = path;
        this.name = name;
        this.valueLeft = valueLeft;
        this.valueRight = valueRight;
        this.left = left;
        this.right = right;
    }

    @Override
    public String getPath() {
        return this.path + "[@" + this.name + ']';
    }

    @Override
    public DiffType getType() {
        return this.diffType;
    }

    @Override
    public AknObject getLeft() {
        return this.left;
    }

    @Override
    public AknObject getRight() {
        return this.right;
    }

    @Override
    public Object getLeftValue() {
        return this.valueLeft;
    }

    @Override
    public Object getRightValue() {
        return this.valueRight;
    }
}
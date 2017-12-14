package io.legaldocml.diff;

import io.legaldocml.akn.AknObject;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class DiffImpl implements Diff {

    private final DiffType type;
    private final AknObject left;
    private final AknObject right;

    DiffImpl(DiffType type, AknObject left, AknObject right) {
        this.type = type;
        this.left = left;
        this.right = right;
    }

}

package io.legaldocml.diff;

import io.legaldocml.akn.AknObject;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Diff {

    String getPath();

    DiffType getType();

    AknObject getLeft();

    AknObject getRight();

}

package io.legaldocml.diff.impl;

import io.legaldocml.akn.AknObject;
import io.legaldocml.diff.DiffType;

final class DeleteElementDiff extends AbstractElementDiff {

    DeleteElementDiff(String path, AknObject left) {
        super(path, left,null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DiffType getType() {
        return DiffType.ELEMENT_DELETED;
    }


}

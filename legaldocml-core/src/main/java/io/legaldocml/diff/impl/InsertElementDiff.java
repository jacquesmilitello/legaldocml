package io.legaldocml.diff.impl;

import io.legaldocml.akn.AknObject;
import io.legaldocml.diff.DiffType;

final class InsertElementDiff extends AbstractElementDiff {

    InsertElementDiff(String path, AknObject left, AknObject right) {
        super(path, null, right);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DiffType getType() {
        return DiffType.ELEMENT_INSERTED;
    }


}

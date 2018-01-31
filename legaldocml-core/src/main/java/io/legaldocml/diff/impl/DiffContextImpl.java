package io.legaldocml.diff.impl;

import io.legaldocml.akn.AknObject;
import io.legaldocml.diff.Diff;
import io.legaldocml.diff.DiffContext;
import io.legaldocml.diff.DiffResult;
import io.legaldocml.diff.DiffType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class DiffContextImpl implements DiffContext {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DiffContext.class);

    private final LinkedList<AknObject> akns = new LinkedList<>();

    private final List<Diff> diffs = new ArrayList<>();

    public <T extends AknObject> void push(T aknObject) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("push({}) -> [{}]", aknObject.name(), path());
        }
        akns.add(aknObject);
    }

    public <T extends AknObject> void missingElement(T aknObject) {
    }

    public <T extends AknObject> void mismatchElement(T aknObject) {
    }

    public <T extends AknObject> void pop(T aknObject) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("pop({}) -> [{}]", aknObject.name(), path());
        }
        akns.removeLast();
    }

    private String path() {
        StringBuilder builder = new StringBuilder(128);
        for (AknObject akn : akns) {
            builder.append('/').append(akn.name());
        }
        return builder.toString();
    }

    public <T extends AknObject> void mismatchElement(T left, T right) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends AknObject> void insertElement(T right) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("element [{}] : inserted [{}]", right.name(), right);
        }

        this.diffs.add(new InsertElementDiff(getPath(), null, right));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends AknObject> void deleteElement(T left) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("element [{}] : deleted [{}]", left.name(), left);
        }

        this.diffs.add(new DeleteElementDiff(getPath(), left));
    }

    @Override
    public <T extends AknObject> void attributeNew(String name, Object valueRight, T left, T right) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("attribute [{}] : New with value [{}] - [{}]", name, valueRight);
        }


        this.diffs.add(new AttributeValueDiff(DiffType.ATTRIBUTE_INSERTED, getPath(), name, null, valueRight, left, right));

    }

    @Override
    public <T extends AknObject> void attributeRemove(String name, Object valueLeft, T left, T right) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("attribute [{}] is removed, old value [{}]", name, valueLeft);
        }


        this.diffs.add(new AttributeValueDiff(DiffType.ATTRIBUTE_REMOVED, getPath(), name, valueLeft, null, left, right));

    }

    public void attributeValueDifferent(String name, Object valueLeft, Object valueRight, AknObject left, AknObject right) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("attribute [{}] has different value [{}] - [{}]", name, valueLeft, valueRight);
        }

        this.diffs.add(new AttributeValueDiff(DiffType.ATTRIBUTE_VALUE, getPath(), name, valueLeft, valueRight, left, right));

    }

    public DiffResult result() {
        return new DiffResultImpl(diffs);
    }

    private String getPath() {
        StringBuilder builder = new StringBuilder();

        for (AknObject akn : akns) {
            builder.append('/').append(akn.name());
        }

        return builder.toString();
    }
}

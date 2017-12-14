package io.legaldocml.diff;

import io.legaldocml.akn.AknObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DiffContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiffContext.class);

    private final LinkedList<AknObject> akns = new LinkedList<>();

    public <T extends AknObject> void push(T aknObject) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("push({}) -> [{}]" , aknObject.name(), path());
        }
        akns.add(aknObject);
    }

    public <T extends AknObject> void missingElement(T aknObject) {
    }

    public <T extends AknObject> void mismatchElement(T aknObject) {
    }

    public <T extends AknObject> void pop(T aknObject) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("pop({}) -> [{}]" , aknObject.name(), path());
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
}

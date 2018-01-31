package io.legaldocml.diff;

import io.legaldocml.akn.AknObject;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface DiffContext {

    <T extends AknObject> void missingElement(T left);

    <T extends AknObject> void mismatchElement(T left, T right);

    <T extends AknObject> void attributeValueDifferent(String name, Object valueLeft, Object valueRight, T left, T right);

    <T extends AknObject> void attributeNew(String name, Object valueRight, T left, T right);

    <T extends AknObject> void attributeRemove(String name, Object valueLeft, T left, T right);

    <T extends AknObject> void push(T object);

    <T extends AknObject> void pop(T object);

    <T extends AknObject> void insertElement(T t);

    <T extends AknObject> void deleteElement(T t);

}

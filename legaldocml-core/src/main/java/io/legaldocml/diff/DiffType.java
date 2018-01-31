package io.legaldocml.diff;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public enum DiffType {
    MISMATCH_ELEMENT, MISSING_ELEMENT,
    ATTRIBUTE_INSERTED, ATTRIBUTE_REMOVED, ELEMENT_INSERTED, ATTRIBUTE_VALUE, ELEMENT_DELETED
}

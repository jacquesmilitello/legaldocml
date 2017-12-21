package io.legaldocml.diff;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface AttributeDiff extends Diff {

    Object getLeftValue();

    Object getRightValue();

}

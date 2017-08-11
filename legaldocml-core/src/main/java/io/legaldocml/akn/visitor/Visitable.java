package io.legaldocml.akn.visitor;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Visitable {

    default void accept(AknVisitor visitor) {
        throw new UnsupportedOperationException("must implements accept for [" + getClass() + "]");
    }

}
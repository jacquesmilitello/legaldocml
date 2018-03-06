package io.legaldocml.akn.util;


import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.akn.visitor.Visitor;

import java.util.Arrays;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AknList<E extends AknObject> extends ExternalizableList<E> {

    public AknList(E[] elem) {
        super(elem);
    }

    private AknList(E[] elem, int size) {
        super(elem, size);
    }

    /**
     * Visit all element in this list with the given {@link Visitor}.
     *
     * @param visitor the visitor for each element.
     */
    public <T extends AknVisitor> void accept(T visitor) {
        E[] elem = this.getElems();
        for (int i = 0, size = size(), n = size; i < n; i++) {
            elem[i].accept(visitor);
        }
    }

    public AknList<E> copy()  {
        return new AknList<E>(Arrays.copyOf(getElems(),size()), size());
    }

}
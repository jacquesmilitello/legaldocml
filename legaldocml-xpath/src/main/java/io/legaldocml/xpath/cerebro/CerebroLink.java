package io.legaldocml.xpath.cerebro;

import io.legaldocml.akn.AknObject;

public interface CerebroLink<E> {

    Class<? extends AknObject> getAknClass();

    Object apply(E e);

}

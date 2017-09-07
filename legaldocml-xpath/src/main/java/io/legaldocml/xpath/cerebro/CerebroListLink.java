package io.legaldocml.xpath.cerebro;

import io.legaldocml.akn.AknObject;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class CerebroListLink<T extends AknObject, E extends AknObject> implements CerebroLink<T> {

    private final Class<E> aknClass;
    private final Function<T, Stream<E>> link;

    public CerebroListLink(Class<E> aknClass, Function<T, Stream<E>> link) {
        this.aknClass = aknClass;
        this.link = link;
    }

    public Class<E> getAknClass() {
        return aknClass;
    }

    @Override
    public Object apply(T t) {

        List list;
        if (t instanceof List) {
            list = (List) ((List)t).stream().map(link).collect(Collectors.toList());
        } else {
            list = this.link.apply(t).collect(Collectors.toList());
        }


        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return list;
    }

}

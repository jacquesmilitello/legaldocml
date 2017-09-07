package io.legaldocml.xpath.cerebro;

import io.legaldocml.akn.AknObject;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class CerebroListFilterLink<T extends AknObject, E extends AknObject, U extends E> implements CerebroLink<T> {

    private final Class<U> aknClass;
    private final Function<T, Stream<E>> link;

    public CerebroListFilterLink(Class<E> base, Class<U> aknClass, Function<T, Stream<E>> link) {
        this.aknClass = aknClass;
        this.link = link;
    }

    public Class<U> getAknClass() {
        return aknClass;
    }

    @Override
    public Object apply(T t) {

        List list;
        if (t instanceof List) {
            list = (List) ((List)t).stream().map(link).filter(p -> aknClass.isAssignableFrom(p.getClass())).collect(Collectors.toList());
        } else {
            list = this.link.apply(t).filter(p -> aknClass.isAssignableFrom(p.getClass())).collect(Collectors.toList());
        }


        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return list;
    }

}

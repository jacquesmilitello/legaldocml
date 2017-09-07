package io.legaldocml.xpath.cerebro;

import io.legaldocml.akn.AknObject;
import io.legaldocml.util.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

final class CerebroDirectLink<T extends AknObject, E extends AknObject> implements CerebroLink<T> {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CerebroDirectLink.class);

    private final Class<E> aknClass;
    private final Function<T, E> link;

    public CerebroDirectLink(Class<E> aknClass, Function<T, E> link) {
        this.aknClass = aknClass;
        this.link = link;
    }

    public Class<E> getAknClass() {
        return aknClass;
    }

    @Override
    public Object apply(T e) {

        if (e instanceof List) {
            return ((List)e).stream().map(this.link).collect(Collectors.toList());
        }

        try {
            return this.link.apply(e);
        } catch (ClassCastException cause) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("ClassCast => return null", cause);
            }
            return null;
        }


    }


    @Override
    public String toString() {
        return new ToStringBuilder(true)
                .append("type", "directLink")
                .append("aknClass", aknClass.getSimpleName())
                .toString();

    }
}

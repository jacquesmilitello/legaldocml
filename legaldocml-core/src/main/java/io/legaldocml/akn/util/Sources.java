package io.legaldocml.akn.util;

import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.attribute.Source;
import io.legaldocml.akn.exception.MandatoryAttributeException;
import io.legaldocml.akn.exception.MetaException;
import io.legaldocml.akn.type.AgentRef;

import static java.util.Objects.requireNonNull;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Sources {

    private Sources() {
    }

    public static <T extends Source> T get(AknList<T> list, AgentRef source) {
        requireNonNull(source);
        if (list == null) {
            return null;
        }
        for (T t : list) {
            if (source.equals(t.getSource())) {
                return t;
            }
        }
        return null;
    }

    public static <T extends Source> void add(AknList<T> list, T item) {
        requireNonNull(list);
        requireNonNull(item);

        AgentRef source = item.getSource();

        if (source == null || source.isEmpty()) {
            throw new MandatoryAttributeException("CoreAttribute 'source' from [ " + item + " shoould not be empty", AknAttributes.SOURCE, item);
        }

        for (T t : list) {
            if (source.equals(t.getSource())) {
                throw new MetaException("Item with source [" + source + "] already exists [" + item + "]");
            }
        }

        list.add(item);
    }
}

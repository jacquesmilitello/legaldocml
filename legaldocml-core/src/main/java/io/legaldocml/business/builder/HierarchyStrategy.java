package io.legaldocml.business.builder;

import com.google.common.collect.ImmutableList;
import io.legaldocml.akn.element.Hierarchy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class HierarchyStrategy {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HierarchyStrategy.class);

    private final ImmutableList<Class<? extends Hierarchy>> keys;
    private final ImmutableList<Supplier<? extends Hierarchy>> values;

    HierarchyStrategy(ImmutableList<Class<? extends Hierarchy>> keys, ImmutableList<Supplier<? extends Hierarchy>> values) {
        this.keys = keys;
        this.values = values;
    }

    @SuppressWarnings("unchecked")
    public <E> E next(Hierarchy hierarchy) {

        int index = this.keys.indexOf(hierarchy.getClass());

        if (index == -1) {
            return null;
        }

        if (index + 1 > this.keys.size()) {
            return null;
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("[{}] -> [{}]", hierarchy.getClass().getSimpleName(), this.keys.get(index + 1).getSimpleName());
        }

        return (E) this.values.get(index + 1).get();
    }

}

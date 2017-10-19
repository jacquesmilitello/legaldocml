package io.legaldocml.business.builder.support;

import io.legaldocml.akn.container.ANhierContainer;
import io.legaldocml.akn.element.List;
import io.legaldocml.business.builder.element.HierarchyBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ListSupport<T extends ANhierContainer> extends SupportBuilder<T> {

    default HierarchyBuilder<List> list() {
        return list(null);
    }

    default HierarchyBuilder<List> list(Consumer<List> consumer) {
        List list = new List();
        parent().add(list);
        if (consumer != null) {
            consumer.accept(list);
        }
        return new HierarchyBuilder<>(businessBuilder(), list);
    }

}

package io.legaldocml.business.builder.support;

import io.legaldocml.akn.container.ANhierContainer;
import io.legaldocml.akn.element.List;
import io.legaldocml.business.builder.HierarchyBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ListSupport<T extends ANhierContainer> extends SupportBuilder<T> {

    default HierarchyBuilder<List> list() {
        List list = new List();
        parent().add(list);
        return new HierarchyBuilder<>(businessBuilder(), list);
    }

}

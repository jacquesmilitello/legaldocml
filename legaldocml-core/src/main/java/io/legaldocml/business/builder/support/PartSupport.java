package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANhierContainer;
import io.legaldocml.akn.element.Part;
import io.legaldocml.business.builder.element.HierarchyBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:mustapha.charboub@gmail.com">Mustapha CHARBOUB</a>
 */
public interface PartSupport<T extends ANhierContainer<E>, E extends AknObject> extends SupportBuilder<T> {

    default HierarchyBuilder part() {
        return part(null);
    }

    default HierarchyBuilder part(Consumer<Part> consumer) {
        Part part = new Part();
        parent().add(part);
        businessBuilder().getContext().push(parent(), part);
        if (consumer != null) {
            consumer.accept(part);
        }
        return new HierarchyBuilder<>(businessBuilder(), part);
    }
}

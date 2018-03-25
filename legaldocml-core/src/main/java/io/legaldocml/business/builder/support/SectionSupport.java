package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANhierContainer;
import io.legaldocml.akn.element.Section;
import io.legaldocml.business.builder.element.HierarchyBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface SectionSupport<T extends ANhierContainer<E>, E extends AknObject> extends SupportBuilder<T> {

    default HierarchyBuilder<Section> section() {
        return section(null);
    }

    default HierarchyBuilder<Section> section(Consumer<Section> consumer) {
        Section section = new Section();
        parent().add(section);
        businessBuilder().getContext().push(parent(), section);
        if (consumer != null) {
            consumer.accept(section);
        }
        return new HierarchyBuilder<>(businessBuilder(), section);
    }

}

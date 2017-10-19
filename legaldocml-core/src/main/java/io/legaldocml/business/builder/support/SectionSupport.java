package io.legaldocml.business.builder.support;

import io.legaldocml.akn.container.ANhierContainer;
import io.legaldocml.akn.element.Section;
import io.legaldocml.business.builder.element.HierarchyBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface SectionSupport<T extends ANhierContainer> extends SupportBuilder<T> {

    default HierarchyBuilder<Section> section() {
        return section(null);
    }

    default HierarchyBuilder<Section> section(Consumer<Section> consumer) {
        Section section = new Section();
        parent().add(section);
        if (consumer != null) {
            consumer.accept(section);
        }
        return new HierarchyBuilder<>(businessBuilder(), section);
    }

}

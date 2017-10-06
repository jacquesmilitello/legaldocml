package io.legaldocml.business.builder.support;

import io.legaldocml.akn.container.ANhierContainer;
import io.legaldocml.akn.element.Chapter;
import io.legaldocml.business.builder.HierarchyBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ChapterSupport<T extends ANhierContainer> extends SupportBuilder<T> {

    default HierarchyBuilder<Chapter> chapter() {
        return chapter(null);
    }

    default HierarchyBuilder<Chapter> chapter(Consumer<Chapter> consumer) {
        Chapter chapter = new Chapter();
        parent().add(chapter);
        if (consumer != null) {
            consumer.accept(chapter);
        }
        return new HierarchyBuilder<>(businessBuilder(), chapter);
    }

}

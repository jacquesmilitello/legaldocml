package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANhierContainer;
import io.legaldocml.akn.element.Title;
import io.legaldocml.business.builder.element.HierarchyBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:mustapha.charboub@gmail.com">Mustapha CHARBOUB</a>
 */
public interface TitleSupport<T extends ANhierContainer<E>, E extends AknObject> extends SupportBuilder<T> {

    default HierarchyBuilder<Title> title() {
        return title(null);
    }

    default HierarchyBuilder<Title> title(Consumer<Title> consumer) {
        Title title = new Title();
        parent().add(title);
        if (consumer != null) {
            consumer.accept(title);
        }
        return new HierarchyBuilder<>(businessBuilder(), title);
    }
}

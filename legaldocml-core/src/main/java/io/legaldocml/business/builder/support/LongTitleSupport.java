package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANhierContainer;
import io.legaldocml.akn.container.BlockElementsContainer;
import io.legaldocml.akn.container.LongTitleContainer;
import io.legaldocml.akn.container.PrefaceContainersContainer;
import io.legaldocml.akn.element.LongTitle;
import io.legaldocml.akn.element.Title;
import io.legaldocml.business.builder.element.BlocksreqBuilder;
import io.legaldocml.business.builder.element.HierarchyBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:mustapha.charboub@gmail.com">Mustapha CHARBOUB</a>
 */
public interface LongTitleSupport<T extends LongTitleContainer<E>, E extends AknObject> extends SupportBuilder<T> {

    default BlocksreqBuilder<LongTitle> longTitle() {
        return longTitle(null);
    }

    default BlocksreqBuilder<LongTitle> longTitle(Consumer<LongTitle> consumer) {
        LongTitle title = new LongTitle();
        parent().add(title);
        if (consumer != null) {
            consumer.accept(title);
        }
        return new BlocksreqBuilder<>(businessBuilder(), title);
    }
}

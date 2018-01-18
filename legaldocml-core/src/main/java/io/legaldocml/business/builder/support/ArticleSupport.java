package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANhierContainer;
import io.legaldocml.akn.element.Article;
import io.legaldocml.business.builder.element.HierarchyBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ArticleSupport<T extends ANhierContainer<E>, E extends AknObject> extends SupportBuilder<T> {

    default HierarchyBuilder<Article> article() {
        return article(null);
    }

    default HierarchyBuilder<Article> article(Consumer<Article> consumer) {
        Article article = new Article();
        parent().add(article);
        if (consumer != null) {
            consumer.accept(article);
        }
        return new HierarchyBuilder<>(businessBuilder(), article);
    }

}

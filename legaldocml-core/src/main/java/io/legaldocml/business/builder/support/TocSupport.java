package io.legaldocml.business.builder.support;

import io.legaldocml.akn.container.ANblockContainer;
import io.legaldocml.akn.element.Toc;
import io.legaldocml.business.builder.element.TocBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface TocSupport<T extends ANblockContainer> extends SupportBuilder<T> {

    default TocBuilder toc() {
        return toc(null);
    }

    default TocBuilder toc(Consumer<Toc> consumer) {
        Toc toc = new Toc();
        parent().add(toc);
        if (consumer != null) {
            consumer.accept(toc);
        }
        return new TocBuilder(businessBuilder(), toc);
    }
}

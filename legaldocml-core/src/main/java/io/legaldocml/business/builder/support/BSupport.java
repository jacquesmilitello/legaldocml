package io.legaldocml.business.builder.support;

import io.legaldocml.akn.container.HTMLinlineContainer;
import io.legaldocml.akn.element.B;
import io.legaldocml.business.builder.element.InlineTypeBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface BSupport<T extends HTMLinlineContainer> extends SupportBuilder<T> {

    default InlineTypeBuilder<B> b() {
        return b(null);
    }

    default InlineTypeBuilder<B> b(Consumer<B> consumer) {
        B b = new B();
        parent().add(b);
        if (consumer != null) {
            consumer.accept(b);
        }
        return new InlineTypeBuilder<>(businessBuilder(), b);
    }

}

package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.HTMLinlineContainer;
import io.legaldocml.akn.element.I;
import io.legaldocml.business.builder.element.InlineTypeBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ISupport<T extends HTMLinlineContainer<E>, E extends AknObject> extends SupportBuilder<T> {

    default InlineTypeBuilder<I> i() {
        return i(null);
    }

    default InlineTypeBuilder<I> i(Consumer<I> consumer) {
        I i = new I();
        parent().add(i);
        if (consumer != null) {
            consumer.accept(i);
        }
        return new InlineTypeBuilder<>(businessBuilder(), i);
    }

}

package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.HTMLblockContainer;
import io.legaldocml.akn.element.P;
import io.legaldocml.business.builder.element.InlineTypeBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface PSupport<T extends HTMLblockContainer<E>, E extends AknObject> extends SupportBuilder<T> {

    default InlineTypeBuilder<P> p() {
        return p(null);
    }

    default InlineTypeBuilder<P> p(Consumer<P> consumer) {
        P p = new P();
        parent().add(p);
        businessBuilder().getContext().push(parent(), p);
        if (consumer != null) {
            consumer.accept(p);
        }
        return new InlineTypeBuilder<>(businessBuilder(), p);
    }

}

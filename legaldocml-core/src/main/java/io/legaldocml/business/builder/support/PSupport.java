package io.legaldocml.business.builder.support;

import io.legaldocml.akn.container.HTMLblockContainer;
import io.legaldocml.akn.element.P;
import io.legaldocml.business.builder.element.PBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface PSupport<T extends HTMLblockContainer> extends SupportBuilder<T> {

    default PBuilder p() {
        return p(null);
    }

    default PBuilder p(Consumer<P> consumer) {
        P p = new P();
        getParent().add(p);
        if (consumer != null) {
            consumer.accept(p);
        }
        return new PBuilder(getBusinessBuilder(), p);
    }

}

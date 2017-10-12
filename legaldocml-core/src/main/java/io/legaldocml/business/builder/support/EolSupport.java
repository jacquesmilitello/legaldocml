package io.legaldocml.business.builder.support;

import io.legaldocml.akn.container.ANmarkerContainer;
import io.legaldocml.akn.element.Eol;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface EolSupport<T extends ANmarkerContainer> extends SupportBuilder<T> {

    default <E extends EolSupport<T>> E eol() {
        return eol(null);
    }

    @SuppressWarnings("unchecked")
    default <E extends EolSupport<T>> E eol(Consumer<Eol> consumer) {
        Eol eol = new Eol();
        parent().add(eol);
        if (consumer != null) {
            consumer.accept(eol);
        }
        return (E)this;
    }

}

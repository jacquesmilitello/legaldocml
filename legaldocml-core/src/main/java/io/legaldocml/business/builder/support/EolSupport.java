package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANmarkerContainer;
import io.legaldocml.akn.element.Eol;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface EolSupport<T extends ANmarkerContainer<E>, E extends AknObject> extends SupportBuilder<T> {

    default <U extends EolSupport<T,E>> U eol() {
        return eol(null);
    }

    @SuppressWarnings("unchecked")
    default <U extends EolSupport<T,E>> U eol(Consumer<Eol> consumer) {
        Eol eol = new Eol();
        parent().add(eol);
        businessBuilder().getContext().push(parent(), eol);
        if (consumer != null) {
            consumer.accept(eol);
        }
        return (U)this;
    }

}

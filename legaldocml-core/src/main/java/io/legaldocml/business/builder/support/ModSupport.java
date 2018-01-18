package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANinlineContainer;
import io.legaldocml.akn.element.Mod;
import io.legaldocml.business.builder.element.ModTypeBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ModSupport<T extends ANinlineContainer<E>, E extends AknObject> extends SupportBuilder<T> {

    default ModTypeBuilder<Mod> mod() {
        return mod(null);
    }

    default ModTypeBuilder<Mod> mod(Consumer<Mod> consumer) {
        Mod mod = new Mod();
        parent().add(mod);
        if (consumer != null) {
            consumer.accept(mod);
        }
        return new ModTypeBuilder<>(businessBuilder(), mod);
    }

}
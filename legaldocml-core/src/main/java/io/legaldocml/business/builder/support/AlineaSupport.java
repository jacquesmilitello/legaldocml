package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANhierContainer;
import io.legaldocml.akn.element.Alinea;
import io.legaldocml.business.builder.element.HierarchyBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:mustapha.charboub@gmail.com">Mustapha CHARBOUB</a>
 */
public interface AlineaSupport<T extends ANhierContainer<E>, E extends AknObject> extends SupportBuilder<T> {

    default HierarchyBuilder<Alinea> alinea() {
        return alinea(null);
    }

    default HierarchyBuilder<Alinea> alinea(Consumer<Alinea> consumer) {
        Alinea alinea = new Alinea();
        parent().add(alinea);
        if (consumer != null) {
            consumer.accept(alinea);
        }
        return new HierarchyBuilder<>(businessBuilder(), alinea);
    }
}

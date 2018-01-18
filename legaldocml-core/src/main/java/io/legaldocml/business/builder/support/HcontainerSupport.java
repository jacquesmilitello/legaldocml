package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.HcontainerContainer;
import io.legaldocml.akn.element.Hcontainer;
import io.legaldocml.business.builder.element.HierarchyBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface HcontainerSupport<T extends HcontainerContainer<E>, E extends AknObject> extends SupportBuilder<T> {

    default HierarchyBuilder<Hcontainer> hcontainer(String name) {
        return hcontainer(name, null);
    }

    default HierarchyBuilder<Hcontainer> hcontainer(String name, Consumer<Hcontainer> consumer) {
        Hcontainer hcontainer = new Hcontainer();
        hcontainer.setName(name);
        parent().add(hcontainer);
        if (consumer != null) {
            consumer.accept(hcontainer);
        }
        return new HierarchyBuilder<>(businessBuilder(), hcontainer);
    }

}

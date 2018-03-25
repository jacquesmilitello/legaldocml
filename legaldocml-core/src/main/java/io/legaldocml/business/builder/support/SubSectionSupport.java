package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANhierContainer;
import io.legaldocml.akn.element.Subsection;
import io.legaldocml.business.builder.element.HierarchyBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface SubSectionSupport<T extends ANhierContainer<E>, E extends AknObject> extends SupportBuilder<T> {

    default HierarchyBuilder<Subsection> subsection() {
        return subsection(null);
    }

    default HierarchyBuilder<Subsection> subsection(Consumer<Subsection> consumer) {
        Subsection subsection = new Subsection();
        parent().add(subsection);
        businessBuilder().getContext().push(parent(), subsection);
        if (consumer != null) {
            consumer.accept(subsection);
        }
        return new HierarchyBuilder<>(businessBuilder(), subsection);
    }

}

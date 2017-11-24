package io.legaldocml.business.builder.support;

import io.legaldocml.akn.container.ContainerContainer;
import io.legaldocml.akn.element.Container;
import io.legaldocml.business.builder.element.ContainerTypeBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ContainerSupport<T extends ContainerContainer> extends SupportBuilder<T> {

    default ContainerTypeBuilder<Container> container(String name) {
        return container(name, null);
    }

    default ContainerTypeBuilder<Container> container(String name, Consumer<Container> consumer) {
        Container container = new Container();
        container.setName(name);
        parent().add(container);
        if (consumer != null) {
            consumer.accept(container);
        }
        return new ContainerTypeBuilder<>(businessBuilder(), container);
    }

}

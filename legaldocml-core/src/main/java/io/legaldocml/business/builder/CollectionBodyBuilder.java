package io.legaldocml.business.builder;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.element.CollectionBody;
import io.legaldocml.akn.element.Component;
import io.legaldocml.business.builder.element.DocContainerTypeBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class CollectionBodyBuilder extends AbstractBusinessPartBuilder<CollectionBody> {

    CollectionBodyBuilder(BusinessBuilder<? extends DocumentType, ? extends BusinessBuilderAkomaNtosoContext> businessBuilder, CollectionBody body) {
        super(businessBuilder, body);
    }

    public DocContainerTypeBuilder<Component> component() {
        return component(null);
    }

    public DocContainerTypeBuilder<Component> component(Consumer<Component> consumer) {
        Component component = new Component();
        parent().add(component);
        if (consumer != null) {
            consumer.accept(component);
        }
        return new DocContainerTypeBuilder<>(businessBuilder(),component);
    }
}
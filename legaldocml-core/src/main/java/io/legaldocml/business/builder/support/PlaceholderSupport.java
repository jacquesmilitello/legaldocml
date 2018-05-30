package io.legaldocml.business.builder.support;

import io.legaldocml.akn.container.InlineCMContainer;
import io.legaldocml.akn.element.Placeholder;
import io.legaldocml.business.builder.element.InlineTypeBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:mustapha.charboub@gmail.com">Mustapha CHARBOUB</a>
 */
public interface PlaceholderSupport<T extends InlineCMContainer> extends SupportBuilder<T> {

    default InlineTypeBuilder<Placeholder> placeholder() {
        return placeholder(null);
    }

    default InlineTypeBuilder<Placeholder> placeholder(Consumer<Placeholder> consumer) {
        Placeholder placeholder = new Placeholder();
        parent().add(placeholder);
        businessBuilder().getContext().push(parent(), placeholder);
        if (consumer != null) {
            consumer.accept(placeholder);
        }
        return new InlineTypeBuilder<>(businessBuilder(), placeholder);
    }
}

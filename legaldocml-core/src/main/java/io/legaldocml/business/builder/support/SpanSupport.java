package io.legaldocml.business.builder.support;

import io.legaldocml.akn.container.InlineCMContainer;
import io.legaldocml.akn.element.Span;
import io.legaldocml.business.builder.element.InlineTypeBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface SpanSupport<T extends InlineCMContainer> extends SupportBuilder<T> {

    default InlineTypeBuilder<Span> span() {
        return span(null);
    }

    default InlineTypeBuilder<Span> span(Consumer<Span> consumer) {
        Span span = new Span();
        parent().add(span);
        if (consumer != null) {
            consumer.accept(span);
        }
        return new InlineTypeBuilder<>(businessBuilder(), span);
    }

}
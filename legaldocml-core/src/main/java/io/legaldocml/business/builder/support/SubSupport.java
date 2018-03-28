package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.HTMLinlineContainer;
import io.legaldocml.akn.element.Sub;
import io.legaldocml.business.builder.element.InlineTypeBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:mustapha.charboub@gmail.com">Mustapha CHARBOUB</a>
 */
public interface SubSupport<T extends HTMLinlineContainer<E>, E extends AknObject> extends SupportBuilder<T> {
    default InlineTypeBuilder<Sub> sub() {
        return sub(null);
    }

    default InlineTypeBuilder<Sub> sub(Consumer<Sub> consumer) {
        Sub sub = new Sub();
        parent().add(sub);
        businessBuilder().getContext().push(parent(), sub);
        if (consumer != null) {
            consumer.accept(sub);
        }
        return new InlineTypeBuilder<>(businessBuilder(), sub);
    }
}

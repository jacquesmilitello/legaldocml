package io.legaldocml.business.builder.support;

import io.legaldocml.akn.container.ANinlineContainer;
import io.legaldocml.akn.element.Mref;
import io.legaldocml.business.builder.InlineTypeBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface MrefSupport<T extends ANinlineContainer> extends SupportBuilder<T> {

    default InlineTypeBuilder<Mref> mref() {
        return mref(null);
    }

    default InlineTypeBuilder<Mref> mref(Consumer<Mref> consumer) {
        Mref mref = new Mref();
        parent().add(mref);
        if (consumer != null) {
            consumer.accept(mref);
        }
        return new InlineTypeBuilder<>(businessBuilder(), mref);
    }

}

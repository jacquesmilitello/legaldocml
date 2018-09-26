package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANsemanticInlineContainer;
import io.legaldocml.akn.element.Date;
import io.legaldocml.business.builder.element.InlineTypeBuilder;

import java.time.temporal.Temporal;
import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface DateSupport<T extends ANsemanticInlineContainer<E>, E extends AknObject> extends SupportBuilder<T> {

    default InlineTypeBuilder<Date> date(Temporal temporal) {
        return date(temporal,null);
    }

    default InlineTypeBuilder<Date> date(Temporal temporal, Consumer<Date> consumer) {
        Date date = new Date();
        date.setDate(temporal);
        parent().add(date);
        businessBuilder().getContext().push(parent(), date);
        if (consumer != null) {
            consumer.accept(date);
        }
        return new InlineTypeBuilder<>(businessBuilder(), date);
    }

}

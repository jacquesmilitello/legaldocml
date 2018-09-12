package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANsemanticInlineContainer;
import io.legaldocml.akn.element.Date;
import io.legaldocml.business.builder.element.InlineTypeBuilder;

import java.time.OffsetDateTime;
import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface DateSupport<T extends ANsemanticInlineContainer<E>, E extends AknObject> extends SupportBuilder<T> {

    default InlineTypeBuilder<Date> date(OffsetDateTime localDate) {
        return date(localDate,null);
    }

    default InlineTypeBuilder<Date> date(OffsetDateTime localDate, Consumer<Date> consumer) {
        Date date = new Date();
        date.setDate(localDate);
        parent().add(date);
        businessBuilder().getContext().push(parent(), date);
        if (consumer != null) {
            consumer.accept(date);
        }
        return new InlineTypeBuilder<>(businessBuilder(), date);
    }

}

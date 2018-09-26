package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANsemanticInlineContainer;
import io.legaldocml.akn.container.AmendmentInlineContainer;
import io.legaldocml.akn.element.Change;
import io.legaldocml.akn.element.Date;
import io.legaldocml.business.builder.element.InlineTypeBuilder;

import java.time.temporal.Temporal;
import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ChangeSupport<T extends AmendmentInlineContainer<E>, E extends AknObject> extends SupportBuilder<T> {

    default InlineTypeBuilder<Change> change() {
        return change(null);
    }

    default InlineTypeBuilder<Change> change(Consumer<Change> consumer) {
        Change change = new Change();
        parent().add(change);
        businessBuilder().getContext().push(parent(), change);
        if (consumer != null) {
            consumer.accept(change);
        }
        return new InlineTypeBuilder<>(businessBuilder(), change);
    }

}

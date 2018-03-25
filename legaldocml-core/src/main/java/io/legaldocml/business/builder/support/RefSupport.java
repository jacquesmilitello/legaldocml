package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANinlineContainer;
import io.legaldocml.akn.element.Ref;
import io.legaldocml.business.builder.element.InlineReqTypeBuilder;
import io.legaldocml.akn.type.Uri;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface RefSupport<T extends ANinlineContainer<E>, E extends AknObject> extends SupportBuilder<T> {

    default InlineReqTypeBuilder<Ref> ref(Uri href) {
        return ref(href, null);
    }

    default InlineReqTypeBuilder<Ref> ref(Uri href, Consumer<Ref> consumer) {
        Ref ref = new Ref();
        ref.setHref(href);
        parent().add(ref);
        businessBuilder().getContext().push(parent(), ref);
        if (consumer != null) {
            consumer.accept(ref);
        }
        return new InlineReqTypeBuilder<>(businessBuilder(), ref);
    }

}
package io.legaldocml.business.builder.support;

import io.legaldocml.akn.container.InlineCMContainer;
import io.legaldocml.akn.element.Ref;
import io.legaldocml.business.builder.InlineReqTypeBuilder;
import io.legaldocml.util.Uri;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface RefSupport<T extends InlineCMContainer> extends SupportBuilder<T> {

    default InlineReqTypeBuilder<Ref> ref(Uri href) {
        return ref(href, null);
    }

    default InlineReqTypeBuilder<Ref> ref(Uri href, Consumer<Ref> consumer) {
        Ref ref = new Ref();
        ref.setHref(href);
        parent().add(ref);
        if (consumer != null) {
            consumer.accept(ref);
        }
        return new InlineReqTypeBuilder<>(businessBuilder(), ref);
    }

}
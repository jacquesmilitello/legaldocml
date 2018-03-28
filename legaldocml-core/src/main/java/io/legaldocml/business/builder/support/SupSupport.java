package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.HTMLinlineContainer;
import io.legaldocml.akn.element.Sup;
import io.legaldocml.business.builder.element.InlineTypeBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:mustapha.charboub@cgi.com">Mustapha CHARBOUB</a>
 */
public interface SupSupport<T extends HTMLinlineContainer<E>, E extends AknObject> extends SupportBuilder<T> {

    default InlineTypeBuilder<Sup> sup() {
        return sup(null);
    }

    default InlineTypeBuilder<Sup> sup(Consumer<Sup> consumer) {
        Sup sup = new Sup();
        parent().add(sup);
        businessBuilder().getContext().push(parent(), sup);
        return new InlineTypeBuilder<>(businessBuilder(), sup);
    }
}

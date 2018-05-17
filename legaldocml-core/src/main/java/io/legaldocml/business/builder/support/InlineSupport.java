package io.legaldocml.business.builder.support;

import io.legaldocml.akn.container.InlineCMContainer;
import io.legaldocml.akn.element.EmbeddedText;
import io.legaldocml.akn.element.Inline;
import io.legaldocml.business.builder.element.InlineTypeBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface InlineSupport<T extends InlineCMContainer> extends SupportBuilder<T> {

    default InlineTypeBuilder<Inline> inline(String name) {
        return inline(name,null);
    }

    default InlineTypeBuilder<Inline> inline(String name, Consumer<Inline> consumer) {
        Inline inline = new Inline();
        inline.setName(name);
        parent().add(inline);
        businessBuilder().getContext().push(parent(), inline);
        if (consumer != null) {
            consumer.accept(inline);
        }
        return new InlineTypeBuilder<>(businessBuilder(), inline);
    }

}

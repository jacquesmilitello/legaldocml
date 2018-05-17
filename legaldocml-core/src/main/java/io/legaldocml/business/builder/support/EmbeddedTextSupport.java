package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.InlineCMContainer;
import io.legaldocml.akn.container.QuotedTextContainer;
import io.legaldocml.akn.element.EmbeddedText;
import io.legaldocml.akn.element.ModTypeItem;
import io.legaldocml.akn.element.QuotedText;
import io.legaldocml.business.builder.element.InlineTypeBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface EmbeddedTextSupport<T extends InlineCMContainer> extends SupportBuilder<T> {

    default InlineTypeBuilder<EmbeddedText> embeddedText() {
        return embeddedText(null);
    }

    default InlineTypeBuilder<EmbeddedText> embeddedText(Consumer<EmbeddedText> consumer) {
        EmbeddedText embeddedText = new EmbeddedText();
        parent().add(embeddedText);
        businessBuilder().getContext().push(parent(), embeddedText);
        if (consumer != null) {
            consumer.accept(embeddedText);
        }
        return new InlineTypeBuilder<>(businessBuilder(), embeddedText);
    }

}

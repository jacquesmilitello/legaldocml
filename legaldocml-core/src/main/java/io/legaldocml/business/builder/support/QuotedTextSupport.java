package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.Container;
import io.legaldocml.akn.container.HTMLinlineContainer;
import io.legaldocml.akn.container.QuotedTextContainer;
import io.legaldocml.akn.element.I;
import io.legaldocml.akn.element.ModTypeItem;
import io.legaldocml.akn.element.QuotedText;
import io.legaldocml.business.builder.element.InlineTypeBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface QuotedTextSupport<T extends QuotedTextContainer<ModTypeItem>, E extends AknObject> extends SupportBuilder<T> {

    default InlineTypeBuilder<QuotedText> quotedText() {
        return quotedText(null);
    }

    default InlineTypeBuilder<QuotedText> quotedText(Consumer<QuotedText> consumer) {
        QuotedText quotedText = new QuotedText();
        parent().add(quotedText);
        businessBuilder().getContext().push(parent(), quotedText);
        if (consumer != null) {
            consumer.accept(quotedText);
        }
        return new InlineTypeBuilder<>(businessBuilder(), quotedText);
    }

}

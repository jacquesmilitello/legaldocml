package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANtitleInlineContainer;
import io.legaldocml.akn.element.DocTitle;
import io.legaldocml.business.builder.element.InlineTypeBuilder;
import io.legaldocml.business.util.AknReference;
import io.legaldocml.business.util.AknReferences;

import java.util.function.Consumer;


/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface DocTitleSupport<T extends ANtitleInlineContainer<E>, E extends AknObject> extends SupportBuilder<T> {

    default InlineTypeBuilder<DocTitle> docTitle(AknReference... refs) {
        return docTitle(null, refs);
    }

    default InlineTypeBuilder<DocTitle> docTitle(Consumer<DocTitle> consumer, AknReference... refs) {
        DocTitle docTitle = new DocTitle();
        parent().add(docTitle);
        businessBuilder().getContext().push(parent(), docTitle);
        AknReferences.apply(businessBuilder().getAkomaNtoso(), docTitle, refs);
        if (consumer != null) {
            consumer.accept(docTitle);
        }
        return new InlineTypeBuilder<>(businessBuilder(), docTitle);
    }


}

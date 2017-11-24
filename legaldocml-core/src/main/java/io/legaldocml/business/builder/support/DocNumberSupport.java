package io.legaldocml.business.builder.support;

import io.legaldocml.akn.container.ANtitleInlineContainer;
import io.legaldocml.akn.element.DocNumber;
import io.legaldocml.business.builder.element.InlineTypeBuilder;
import io.legaldocml.business.util.AknReference;
import io.legaldocml.business.util.AknReferences;

import java.util.function.Consumer;


/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface DocNumberSupport<T extends ANtitleInlineContainer> extends SupportBuilder<T> {

    default InlineTypeBuilder<DocNumber> docNumber(AknReference... refs) {
        return docNumber(null, refs);
    }

    default InlineTypeBuilder<DocNumber> docNumber(Consumer<DocNumber> consumer, AknReference... refs) {
        DocNumber docNumber = new DocNumber();
        parent().add(docNumber);
        AknReferences.apply(businessBuilder().getAkomaNtoso(), docNumber, refs);
        if (consumer != null) {
            consumer.accept(docNumber);
        }
        return new InlineTypeBuilder<>(businessBuilder(), docNumber);
    }


}

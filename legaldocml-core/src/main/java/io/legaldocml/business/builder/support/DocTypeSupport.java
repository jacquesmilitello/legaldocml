package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANtitleInlineContainer;
import io.legaldocml.akn.element.DocType;
import io.legaldocml.business.builder.element.InlineTypeBuilder;
import io.legaldocml.business.util.AknReference;
import io.legaldocml.business.util.AknReferences;

import java.util.function.Consumer;


/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface DocTypeSupport<T extends ANtitleInlineContainer<E>, E extends AknObject> extends SupportBuilder<T>  {

    default InlineTypeBuilder<DocType> docType(Consumer<DocType> consumer, AknReference... refs) {
        DocType docType = new DocType();
        parent().add(docType);
        AknReferences.apply(businessBuilder().getAkomaNtoso(), docType, refs);
        if (consumer != null) {
            consumer.accept(docType);
        }
        return new InlineTypeBuilder<>(businessBuilder(), docType);
    }

    default InlineTypeBuilder<DocType> docType(AknReference... refs) {
        return docType(null, refs);
    }

}

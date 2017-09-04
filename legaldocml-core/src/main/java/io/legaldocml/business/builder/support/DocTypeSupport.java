package io.legaldocml.business.builder.support;

import io.legaldocml.akn.container.ANtitleInlineContainer;
import io.legaldocml.akn.element.DocType;
import io.legaldocml.business.builder.InlineTypeBuilder;
import io.legaldocml.business.util.AknReference;
import io.legaldocml.business.util.AknReferenceHelper;

import java.util.function.Consumer;


/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface DocTypeSupport<T extends ANtitleInlineContainer> extends SupportBuilder<T>  {

    @SuppressWarnings("unchecked")
    default InlineTypeBuilder<DocType> docType(Consumer<DocType> consumer, AknReference... refs) {
        DocType docType = new DocType();
        getParent().add(docType);
        AknReferenceHelper.apply(getBusinessBuilder().getAkomaNtoso(), docType, refs);
        if (consumer != null) {
            consumer.accept(docType);
        }
        return new InlineTypeBuilder<>(getBusinessBuilder(), docType);
    }

    default InlineTypeBuilder<DocType> docType(AknReference... refs) {
        return docType(null, refs);
    }

}

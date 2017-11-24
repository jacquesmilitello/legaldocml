package io.legaldocml.business.builder.support;

import io.legaldocml.akn.container.ANtitleInlineContainer;
import io.legaldocml.akn.element.DocDate;
import io.legaldocml.business.builder.element.InlineTypeBuilder;
import io.legaldocml.business.util.AknReference;
import io.legaldocml.business.util.AknReferences;

import java.time.OffsetDateTime;
import java.util.function.Consumer;


/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface DocDateSupport<T extends ANtitleInlineContainer> extends SupportBuilder<T> {

    default InlineTypeBuilder<DocDate> docDate(OffsetDateTime date, AknReference... refs) {
        return docDate(date,null, refs);
    }

    default InlineTypeBuilder<DocDate> docDate(OffsetDateTime date, Consumer<DocDate> consumer, AknReference... refs) {
        DocDate docDate = new DocDate();
        docDate.setDate(date);
        parent().add(docDate);
        AknReferences.apply(businessBuilder().getAkomaNtoso(), docDate, refs);
        if (consumer != null) {
            consumer.accept(docDate);
        }
        return new InlineTypeBuilder<>(businessBuilder(), docDate);
    }


}

package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANtitleInlineContainer;
import io.legaldocml.akn.element.DocCommittee;
import io.legaldocml.business.builder.element.InlineTypeBuilder;
import io.legaldocml.business.util.AknReference;
import io.legaldocml.business.util.AknReferences;

import java.util.function.Consumer;


/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface DocCommitteeSupport<T extends ANtitleInlineContainer<E>, E extends AknObject> extends SupportBuilder<T> {

    default InlineTypeBuilder<DocCommittee> docCommittee(AknReference... refs) {
        return docCommittee(null, refs);
    }

    default InlineTypeBuilder<DocCommittee> docCommittee(Consumer<DocCommittee> consumer, AknReference... refs) {
        DocCommittee docCommittee = new DocCommittee();
        parent().add(docCommittee);
        AknReferences.apply(businessBuilder().getAkomaNtoso(), docCommittee, refs);
        if (consumer != null) {
            consumer.accept(docCommittee);
        }
        return new InlineTypeBuilder<>(businessBuilder(), docCommittee);
    }


}

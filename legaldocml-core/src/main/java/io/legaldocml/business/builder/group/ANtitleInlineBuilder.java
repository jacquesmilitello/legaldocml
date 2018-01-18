package io.legaldocml.business.builder.group;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANtitleInlineContainer;
import io.legaldocml.business.builder.support.DocCommitteeSupport;
import io.legaldocml.business.builder.support.DocDateSupport;
import io.legaldocml.business.builder.support.DocNumberSupport;
import io.legaldocml.business.builder.support.DocProponentSupport;
import io.legaldocml.business.builder.support.DocTitleSupport;
import io.legaldocml.business.builder.support.DocTypeSupport;
import io.legaldocml.business.builder.support.DocketNumberSupport;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANtitleInlineBuilder<T extends ANtitleInlineContainer<E>, E extends AknObject> extends DocTypeSupport<T,E>, DocNumberSupport<T,E>,
        DocTitleSupport<T,E>, DocProponentSupport<T,E>, DocCommitteeSupport<T,E>, DocketNumberSupport<T,E>, DocDateSupport<T,E> {

}

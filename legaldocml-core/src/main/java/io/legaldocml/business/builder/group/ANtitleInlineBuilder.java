package io.legaldocml.business.builder.group;

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
public interface ANtitleInlineBuilder<T extends ANtitleInlineContainer> extends DocTypeSupport<T>, DocNumberSupport<T>,
        DocTitleSupport<T>, DocProponentSupport<T>, DocCommitteeSupport<T>, DocketNumberSupport<T>, DocDateSupport<T> {

}

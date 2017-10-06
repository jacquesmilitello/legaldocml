package io.legaldocml.business.builder.group;

import io.legaldocml.akn.container.ANtitleInlineContainer;
import io.legaldocml.business.builder.support.DocNumberSupport;
import io.legaldocml.business.builder.support.DocProponentSupport;
import io.legaldocml.business.builder.support.DocTitleSupport;
import io.legaldocml.business.builder.support.DocTypeSupport;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANtitleInlineBuilder<T extends ANtitleInlineContainer> extends DocTypeSupport<T>, DocNumberSupport<T>, DocTitleSupport<T>, DocProponentSupport<T> {

}

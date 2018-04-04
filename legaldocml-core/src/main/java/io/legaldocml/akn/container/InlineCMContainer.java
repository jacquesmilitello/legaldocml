package io.legaldocml.akn.container;

import io.legaldocml.akn.group.InlineCM;

/**
 * Markup interface for :
 * {@code {@link io.legaldocml.akn.element.InlineType }}
 * {@code {@link io.legaldocml.akn.element.InlineReqType }}
 * {@code {@link io.legaldocml.akn.element.InlineReqReqType }}
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface InlineCMContainer extends SubFlowsElementsContainer<InlineCM> , InlineElementsContainer<InlineCM>, MarkerElementsContainer<InlineCM> {

    void add(InlineCM inlineCM);

}

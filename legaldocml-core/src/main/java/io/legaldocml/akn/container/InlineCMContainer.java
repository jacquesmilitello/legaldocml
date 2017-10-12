package io.legaldocml.akn.container;

import io.legaldocml.akn.element.InlineReqReqType;
import io.legaldocml.akn.element.InlineReqType;
import io.legaldocml.akn.element.InlineType;
import io.legaldocml.akn.group.InlineCM;

/**
 * Markup interface for :
 * {@code {@link InlineType }}
 * {@code {@link InlineReqType }}
 * {@code {@link InlineReqReqType }}
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface InlineCMContainer extends SubFlowsElementsContainer , InlineElementsContainer, MarkerElementsContainer {

    void add(InlineCM inlineCM);

}

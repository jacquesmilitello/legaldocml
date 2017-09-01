package io.legaldocml.business.builder.support;

import io.legaldocml.akn.container.SubFlowsElementsContainer;
import io.legaldocml.akn.element.SubFlow;
import io.legaldocml.business.builder.element.SubFlowBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface SubFlowSupport<T extends SubFlowsElementsContainer> extends SupportBuilder<T> {

    default SubFlowBuilder subFlow(String name) {
        SubFlow subFlow = new SubFlow();
        subFlow.setName(name);
        getParent().add(subFlow);
        return new SubFlowBuilder(getBusinessBuilder(), subFlow);
    }

}

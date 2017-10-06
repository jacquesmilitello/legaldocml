package io.legaldocml.business.builder.element;

import io.legaldocml.akn.element.SubFlow;
import io.legaldocml.business.builder.AbstractBusinessPartBuilder;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.business.builder.support.ForeignSupport;
import io.legaldocml.business.builder.support.ListSupport;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class SubFlowBuilder extends AbstractBusinessPartBuilder<SubFlow> implements ForeignSupport<SubFlow> ,
        ListSupport<SubFlow> {

    public SubFlowBuilder(BusinessBuilder businessBuilder, SubFlow parent) {
        super(businessBuilder, parent);
    }

}

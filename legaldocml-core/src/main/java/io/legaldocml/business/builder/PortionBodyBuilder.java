package io.legaldocml.business.builder;

import io.legaldocml.akn.element.PortionBody;
import io.legaldocml.business.builder.group.HierElementsBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class PortionBodyBuilder extends AbstractBusinessPartBuilder<PortionBody> implements HierElementsBuilder<PortionBody> {

    protected PortionBodyBuilder(BusinessBuilder businessBuilder, PortionBody portionBody) {
        super(businessBuilder, portionBody);
    }

}
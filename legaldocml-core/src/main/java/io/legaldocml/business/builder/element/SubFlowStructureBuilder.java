package io.legaldocml.business.builder.element;

import io.legaldocml.akn.element.SubFlowStructure;
import io.legaldocml.business.builder.AbstractBusinessPartBuilder;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.business.builder.group.HierElementsBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class SubFlowStructureBuilder<T extends SubFlowStructure> extends AbstractBusinessPartBuilder<T> implements HierElementsBuilder<T> {

    private final T structure;

    public SubFlowStructureBuilder(BusinessBuilder businessBuilder, T structure) {
        super(businessBuilder,structure);
        this.structure = structure;
    }

}

package io.legaldocml.business.builder.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.element.SubFlowStructure;
import io.legaldocml.akn.element.SubFlowStructureElement;
import io.legaldocml.business.builder.AbstractBusinessPartBuilder;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.business.builder.group.HierElementsBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class SubFlowStructureBuilder<T extends SubFlowStructure> extends AbstractBusinessPartBuilder<T> implements HierElementsBuilder<T, SubFlowStructureElement> {

    private final T structure;

    public SubFlowStructureBuilder(BusinessBuilder<? extends DocumentType> businessBuilder, T structure) {
        super(businessBuilder,structure);
        this.structure = structure;
    }

}

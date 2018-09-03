package io.legaldocml.business.builder.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.element.SubFlowStructure;
import io.legaldocml.akn.element.SubFlowStructureElement;
import io.legaldocml.business.builder.AbstractBusinessPartBuilder;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.business.builder.BusinessBuilderAkomaNtosoContext;
import io.legaldocml.business.builder.group.BlockElementsBuilder;
import io.legaldocml.business.builder.group.HierElementsBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class SubFlowStructureBuilder<T extends SubFlowStructure> extends AbstractBusinessPartBuilder<T> implements HierElementsBuilder<T, SubFlowStructureElement>, BlockElementsBuilder<T, SubFlowStructureElement> {

    public SubFlowStructureBuilder(BusinessBuilder<? extends DocumentType, ? extends BusinessBuilderAkomaNtosoContext> businessBuilder, T structure) {
        super(businessBuilder,structure);
    }

}

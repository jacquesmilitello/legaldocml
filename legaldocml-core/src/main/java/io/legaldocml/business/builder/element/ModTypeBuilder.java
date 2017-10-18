package io.legaldocml.business.builder.element;

import io.legaldocml.akn.element.ModType;
import io.legaldocml.akn.element.QuotedStructureV3;
import io.legaldocml.business.builder.AbstractBusinessPartBuilder;
import io.legaldocml.business.builder.BusinessBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ModTypeBuilder<T extends ModType> extends AbstractBusinessPartBuilder<T> {

    private final T modType;

    public ModTypeBuilder(BusinessBuilder businessBuilder, T modType) {
        super(businessBuilder,modType);
        this.modType = modType;
    }

    public SubFlowStructureBuilder<QuotedStructureV3> quotedStructure() {
        return quotedStructure(null);
    }

    public SubFlowStructureBuilder<QuotedStructureV3> quotedStructure(Consumer<QuotedStructureV3> consumer) {
        QuotedStructureV3 mod = new QuotedStructureV3();
        modType.add(mod);
        if (consumer != null) {
            consumer.accept(mod);
        }
        return new SubFlowStructureBuilder<>(businessBuilder(), mod);
    }

}

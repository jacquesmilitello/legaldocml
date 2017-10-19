package io.legaldocml.business.builder;

import io.legaldocml.akn.HasPreamble;
import io.legaldocml.akn.element.Preamble;
import io.legaldocml.business.builder.group.BlockElementsBuilder;
import io.legaldocml.business.builder.group.PreambleContainersBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class PreambleBuilder extends AbstractBusinessPartBuilder<Preamble> implements BlockElementsBuilder<Preamble>, PreambleContainersBuilder<Preamble> {

    public PreambleBuilder(BusinessBuilder builder) {
        super(builder, new Preamble());
        if (!(builder.getAkomaNtoso().getDocumentType() instanceof HasPreamble)) {
            throw new BusinessBuilderException("DocumentType [" + builder.getAkomaNtoso().getDocumentType().getClass().getSimpleName() + "] has no Preamble");
        }
        ((HasPreamble) builder.getAkomaNtoso().getDocumentType()).setPreamble(parent());
    }

}
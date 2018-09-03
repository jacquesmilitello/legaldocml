package io.legaldocml.business.builder;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.HasPreface;
import io.legaldocml.akn.element.Preface;
import io.legaldocml.akn.element.PrefaceoptElement;
import io.legaldocml.business.builder.group.BlockElementsBuilder;
import io.legaldocml.business.builder.group.PrefaceContainersBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class PrefaceBuilder extends AbstractBusinessPartBuilder<Preface> implements BlockElementsBuilder<Preface,PrefaceoptElement>, PrefaceContainersBuilder<Preface,PrefaceoptElement> {

    public PrefaceBuilder(BusinessBuilder<? extends DocumentType, ? extends BusinessBuilderAkomaNtosoContext> builder) {
        super(builder, new Preface());
        if (!(builder.getAkomaNtoso().getDocumentType() instanceof HasPreface)) {
            throw new BusinessBuilderException("DocumentType [" + builder.getAkomaNtoso().getDocumentType().getClass().getSimpleName() + "] has no Preface");
        }
        ((HasPreface) builder.getAkomaNtoso().getDocumentType()).setPreface(parent());
    }

}
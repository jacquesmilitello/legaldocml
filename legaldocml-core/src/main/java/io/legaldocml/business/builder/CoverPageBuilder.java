package io.legaldocml.business.builder;

import io.legaldocml.akn.HasCoverPage;
import io.legaldocml.akn.element.BasicoptElement;
import io.legaldocml.akn.element.CoverPage;
import io.legaldocml.business.builder.support.PSupport;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class CoverPageBuilder extends AbstractBusinessPartBuilder<CoverPage> implements PSupport<CoverPage, BasicoptElement> {

    public CoverPageBuilder(BusinessBuilder builder) {
        super(builder, new CoverPage());
        if (!(builder.getAkomaNtoso().getDocumentType() instanceof HasCoverPage)) {
            throw new BusinessBuilderException("DocumentType [" + builder.getAkomaNtoso().getDocumentType().getClass().getSimpleName() + "] has no CoverPage");
        }
        ((HasCoverPage) builder.getAkomaNtoso().getDocumentType()).setCoverPage(parent());
    }

}
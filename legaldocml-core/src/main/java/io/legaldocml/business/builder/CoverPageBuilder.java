package io.legaldocml.business.builder;

import io.legaldocml.akn.HasCoverPage;
import io.legaldocml.akn.element.CoverPage;
import io.legaldocml.akn.element.P;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class CoverPageBuilder implements HasPBuilder {

    private final CoverPage coverPage;

    private final BusinessBuilder businessBuilder;

    public CoverPageBuilder(BusinessBuilder builder) {

        if (!(builder.getAkomaNtoso().getDocumentType() instanceof HasCoverPage)) {
            throw new BusinessBuilderException("DocumentType [" + builder.getAkomaNtoso().getDocumentType().getClass().getSimpleName() + "] has no CoverPage");
        }
        this.businessBuilder = builder;
        this.coverPage = new CoverPage();
        ((HasCoverPage) builder.getAkomaNtoso().getDocumentType()).setCoverPage(coverPage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PBuilder p() {
        P p = new P();
        this.coverPage.add(p);
        return new PBuilder(p, businessBuilder);
    }

}
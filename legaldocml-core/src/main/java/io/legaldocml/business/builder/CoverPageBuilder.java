package io.legaldocml.business.builder;

import io.legaldocml.akn.element.CoverPage;
import io.legaldocml.akn.element.P;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class CoverPageBuilder {

    private final CoverPage coverPage;

    private final BusinessBuilder businessBuilder;

    public CoverPageBuilder(BusinessBuilder builder, CoverPage coverPage) {
        this.businessBuilder = builder;
        this.coverPage = coverPage;
    }

    public PBuilder p() {
        P p = new P();
        this.coverPage.add(p);
        return new PBuilder(p, businessBuilder);
    }

}

package io.legaldocml.business.builder;

import io.legaldocml.akn.element.B;
import io.legaldocml.akn.element.DocNumber;
import io.legaldocml.akn.element.DocProponent;
import io.legaldocml.akn.element.DocTitle;
import io.legaldocml.akn.element.DocType;
import io.legaldocml.akn.element.P;
import io.legaldocml.akn.element.StringInlineCM;
import io.legaldocml.business.util.AknReference;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class PBuilder {

    private final P p;
    private final BusinessBuilder businessBuilder;

    public PBuilder(P p, BusinessBuilder businessBuilder) {
        this.p = p;
        this.businessBuilder = businessBuilder;
    }

    public PBuilder text(String text) {
        this.p.add(new StringInlineCM(text));
        return this;
    }

    public PBuilder docType(String text) {
        DocType docType = new DocType();
        docType.add(new StringInlineCM(text));
        this.p.add(docType);
        return this;
    }

    @SuppressWarnings("unchecked")
    public PBuilder docProponent(String text, AknReference... refs) {
        DocProponent docProponent = new DocProponent();
        docProponent.add(new StringInlineCM(text));

        if (refs != null) {
            for (AknReference aknReferences : refs) {
                aknReferences.accept(docProponent, this.businessBuilder.getAkomaNtoso());
            }
        }

        this.p.add(docProponent);
        return this;
    }

    public PBuilder docNumber(String text) {
        DocNumber number = new DocNumber();
        number.add(new StringInlineCM(text));
        this.p.add(number);
        return this;
    }

    public PBuilder docTitle(String text) {
        DocTitle title = new DocTitle();
        title.add(new StringInlineCM(text));
        this.p.add(title);
        return this;
    }

    public PBuilder b(String text) {
        B b = new B();
        b.add(new StringInlineCM(text));
        this.p.add(b);
        return this;
    }
}

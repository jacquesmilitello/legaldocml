package io.legaldocml.business.builder.element;

import io.legaldocml.akn.element.AuthorialNote;
import io.legaldocml.akn.element.B;
import io.legaldocml.akn.element.DocNumber;
import io.legaldocml.akn.element.DocProponent;
import io.legaldocml.akn.element.DocTitle;
import io.legaldocml.akn.element.DocType;
import io.legaldocml.akn.element.I;
import io.legaldocml.akn.element.Inline;
import io.legaldocml.akn.element.P;
import io.legaldocml.akn.element.StringInlineCM;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.business.builder.InlineTypeBuilder;
import io.legaldocml.business.builder.support.SubFlowSupport;
import io.legaldocml.business.util.AknReference;
import io.legaldocml.business.util.AknReferenceHelper;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class PBuilder extends ElementBuilder<P> implements SubFlowSupport<P> {

    public PBuilder(BusinessBuilder businessBuilder, P p) {
        super(businessBuilder, p);
    }

    public PBuilder text(String text) {
        getParent().add(new StringInlineCM(text));
        return this;
    }

    public PBuilder docType(String text) {
        DocType docType = new DocType();
        docType.add(new StringInlineCM(text));
        getParent().add(docType);
        return this;
    }

    @SuppressWarnings("unchecked")
    public PBuilder docProponent(String text, AknReference... refs) {
        DocProponent docProponent = new DocProponent();
        docProponent.add(new StringInlineCM(text));
        getParent().add(docProponent);
        AknReferenceHelper.apply(getBusinessBuilder().getAkomaNtoso(), docProponent, refs);
        return this;
    }

    public PBuilder docNumber(String text) {
        DocNumber number = new DocNumber();
        number.add(new StringInlineCM(text));
        getParent().add(number);
        return this;
    }

    public PBuilder docTitle(String text) {
        DocTitle title = new DocTitle();
        title.add(new StringInlineCM(text));
        getParent().add(title);
        return this;
    }

    public PBuilder b(String text) {
        b().text(text);
        return this;
    }

    public InlineTypeBuilder<B> b() {
        B b = new B();
        getParent().add(b);
        return new InlineTypeBuilder<>(getBusinessBuilder(), b);
    }

    public PBuilder i(String text) {
        i().text(text);
        return this;
    }

    public InlineTypeBuilder<I> i() {
        I i = new I();
        getParent().add(i);
        return new InlineTypeBuilder<>(getBusinessBuilder(), i);
    }

    @SuppressWarnings("unchecked")
    public InlineTypeBuilder<Inline> inline(String name, AknReference... refs) {
        Inline inline = new Inline();
        getParent().add(inline);
        inline.setName(name);
        AknReferenceHelper.apply(getBusinessBuilder().getAkomaNtoso(), inline, refs);
        return new InlineTypeBuilder<>(getBusinessBuilder(), inline);
    }

    public PBuilder authorialNote() {
        return this.authorialNote(null);
    }

    public PBuilder authorialNote(Consumer<AuthorialNote> consumer) {
        AuthorialNote note = new AuthorialNote();
        getParent().add(note);
        P noteP = new P();
        note.add(noteP);
        if (consumer != null) {
            consumer.accept(note);
        }
        return new PBuilder(getBusinessBuilder(), noteP);
    }

}

package io.legaldocml.business.builder;

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
import io.legaldocml.business.util.AknReference;
import io.legaldocml.business.util.AknReferenceHelper;

import java.util.function.Consumer;

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
        this.p.add(docProponent);
        AknReferenceHelper.apply(this.businessBuilder.getAkomaNtoso(), docProponent, refs);
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
        b().text(text);
        return this;
    }

    public InlineTypeBuilder<B> b() {
        B b = new B();
        this.p.add(b);
        return new InlineTypeBuilder<>(this.businessBuilder, b);
    }

    public PBuilder i(String text) {
        i().text(text);
        return this;
    }

    public InlineTypeBuilder<I> i() {
        I i = new I();
        this.p.add(i);
        return new InlineTypeBuilder<>(this.businessBuilder, i);
    }

    @SuppressWarnings("unchecked")
    public InlineTypeBuilder<Inline> inline(String name, AknReference... refs) {
        Inline inline = new Inline();
        this.p.add(inline);
        inline.setName(name);
        AknReferenceHelper.apply(this.businessBuilder.getAkomaNtoso(), inline, refs);
        return new InlineTypeBuilder<>(this.businessBuilder, inline);
    }

    public PBuilder authorialNote() {
        return this.authorialNote(null);
    }

    public PBuilder authorialNote(Consumer<AuthorialNote> consumer) {
        AuthorialNote note = new AuthorialNote();
        this.p.add(note);
        P noteP = new P();
        note.add(noteP);
        if (consumer != null) {
            consumer.accept(note);
        }
        return new PBuilder(noteP, businessBuilder);
    }




}

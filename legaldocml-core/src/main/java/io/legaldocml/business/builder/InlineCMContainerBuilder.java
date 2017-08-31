package io.legaldocml.business.builder;

import io.legaldocml.akn.element.B;
import io.legaldocml.akn.element.Eol;
import io.legaldocml.akn.element.I;
import io.legaldocml.akn.element.Inline;
import io.legaldocml.akn.element.InlineCMContainer;
import io.legaldocml.akn.element.StringInlineCM;
import io.legaldocml.akn.element.Sup;
import io.legaldocml.business.util.AknReference;
import io.legaldocml.business.util.AknReferenceHelper;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class InlineCMContainerBuilder<T extends InlineCMContainer, E extends InlineCMContainerBuilder<T,E>> {

    private final T container;

    private final BusinessBuilder businessBuilder;

    public InlineCMContainerBuilder(BusinessBuilder businessBuilder, T container) {
        this.container = container;
        this.businessBuilder = businessBuilder;
    }

    @SuppressWarnings("unchecked")
    public E text(String text) {
        this.container.add(new StringInlineCM(text));
        return (E) this;
    }

    @SuppressWarnings("unchecked")
    public E i() {
        I i  = new I();
        this.container.add(i);
        return (E) this;
    }

    @SuppressWarnings("unchecked")
    public E b() {
        B b  = new B();
        this.container.add(b);
        return (E) this;
    }

    @SuppressWarnings("unchecked")
    public E sup() {
        Sup sup  = new Sup();
        this.container.add(sup);
        return (E) this;
    }

    @SuppressWarnings("unchecked")
    public E eol() {
        Eol eol = new Eol();
        this.container.add(eol);
        return (E) this;
    }

    @SuppressWarnings("unchecked")
    public E inline(String name, AknReference... refs) {
        Inline inline = new Inline();
        this.container.add(inline);
        inline.setName(name);
        AknReferenceHelper.apply(this.businessBuilder.getAkomaNtoso(), inline, refs);
        return (E) this;
    }

}

package io.legaldocml.business.builder;

import io.legaldocml.akn.element.Eol;
import io.legaldocml.akn.element.I;
import io.legaldocml.akn.element.InlineCMContainer;
import io.legaldocml.akn.element.StringInlineCM;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class InlineCMContainerBuilder<T extends InlineCMContainer, E extends InlineCMContainerBuilder<T,E>> {

    private final T container;

    public InlineCMContainerBuilder(T container) {
        this.container = container;
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
    public E eol() {
        Eol eol = new Eol();
        this.container.add(eol);
        return (E) this;
    }

}

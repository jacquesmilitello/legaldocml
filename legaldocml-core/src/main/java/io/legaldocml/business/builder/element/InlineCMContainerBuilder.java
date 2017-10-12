package io.legaldocml.business.builder.element;

import io.legaldocml.akn.container.InlineCMContainer;
import io.legaldocml.akn.element.I;
import io.legaldocml.akn.element.Inline;
import io.legaldocml.akn.element.StringInlineCM;
import io.legaldocml.akn.element.Sup;
import io.legaldocml.business.builder.AbstractBusinessPartBuilder;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.business.builder.BusinessPartBuilder;
import io.legaldocml.business.builder.group.ANinlineBuilder;
import io.legaldocml.business.builder.group.ANtitleInlineBuilder;
import io.legaldocml.business.builder.group.HTMLInlineBuilder;
import io.legaldocml.business.builder.group.MarkerElementsBuilder;
import io.legaldocml.business.builder.support.OrganizationSupport;
import io.legaldocml.business.util.AknReference;
import io.legaldocml.business.util.AknReferenceHelper;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class InlineCMContainerBuilder<T extends InlineCMContainer, U extends BusinessPartBuilder<T>> extends AbstractBusinessPartBuilder<T>
        implements ANtitleInlineBuilder<T>, ANinlineBuilder<T>, HTMLInlineBuilder<T>, OrganizationSupport<T>, MarkerElementsBuilder<T> {

    private final T container;

    public InlineCMContainerBuilder(BusinessBuilder businessBuilder, T container) {
        super(businessBuilder, container);
        this.container = container;
    }

    @SuppressWarnings("unchecked")
    public U text(String text) {
        this.container.add(new StringInlineCM(text));
        return (U) this;
    }

    @SuppressWarnings("unchecked")
    public U i() {
        I i = new I();
        this.container.add(i);
        return (U) this;
    }

    @SuppressWarnings("unchecked")
    public U sup() {
        Sup sup = new Sup();
        this.container.add(sup);
        return (U) this;
    }


    @SuppressWarnings("unchecked")
    public U inline(String name, AknReference... refs) {
        Inline inline = new Inline();
        this.container.add(inline);
        inline.setName(name);
        AknReferenceHelper.apply(getBusinessBuilder().getAkomaNtoso(), inline, refs);
        return (U) this;
    }


}
